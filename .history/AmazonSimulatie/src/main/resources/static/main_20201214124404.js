import * as THREE from "./three.js-master/build/three.module.js";
import { GLTFLoader } from "./three.js-master/examples/jsm/loaders/GLTFLoader.js";
import { OrbitControls } from "./three.js-master/examples/jsm/controls/OrbitControls.js";
import robotCreator from "./RobotCreator.js";

    let robotMaterials = [new THREE.MeshBasicMaterial({map: new THREE.TextureLoader().load("textures/robot_side.png"),side: THREE.DoubleSide,}), //LEFT
    new THREE.MeshBasicMaterial({map: new THREE.TextureLoader().load("textures/robot_side.png"), side: THREE.DoubleSide,}), //RIGHT
    new THREE.MeshBasicMaterial({map: new THREE.TextureLoader().load("textures/robot_top.png"), side: THREE.DoubleSide,}), //TOP
    new THREE.MeshBasicMaterial({map: new THREE.TextureLoader().load("textures/robot_bottom.png"),side: THREE.DoubleSide,}), //BOTTOM
    new THREE.MeshBasicMaterial({map: new THREE.TextureLoader().load("textures/robot_front.png"),side: THREE.DoubleSide,}), //FRONT
    new THREE.MeshBasicMaterial({map: new THREE.TextureLoader().load("textures/robot_front.png"),side: THREE.DoubleSide,}), //BACK
    ];
    let gltfLoader = new GLTFLoader();
    function parseCommand(input = "") {
    console.log(JSON.parse(input));
    return JSON.parse(input);
    }

    let socket;
    window.onload = function () {
    var camera, scene, renderer;
    var cameraControls;
    var worldObjects = {};

    function init() {
        scene = new THREE.Scene();
        scene.background = new THREE.Color(0xdddddd);
        renderer = new THREE.WebGLRenderer({ antialias: true });
        renderer.toneMapping = THREE.ReinhardToneMapping;
        renderer.toneMappingExposure = 2.3;
        renderer.setPixelRatio(window.devicePixelRatio);
        renderer.setSize(window.innerWidth, window.innerHeight + 5);
        document.body.appendChild(renderer.domElement);
        camera = new THREE.PerspectiveCamera(70, window.innerWidth / window.innerHeight,1,1000);
        cameraControls = new OrbitControls(camera, renderer.domElement);
        camera.position.z = 15;
        camera.position.y = 50;
        camera.position.x = 15;
        cameraControls.update();
        window.addEventListener("resize", onWindowResize, false);
        var hemiLight = new THREE.HemisphereLight(0xffeeb1, 0x080820, 4);
        hemiLight.intensity = 5;
        scene.add(hemiLight);
    }
    function onWindowResize() {
        camera.aspect = window.innerWidth / window.innerHeight;
        camera.updateProjectionMatrix();
        renderer.setSize(window.innerWidth, window.innerHeight);
    }
    function animate() {
        requestAnimationFrame(animate);
        cameraControls.update();
        renderer.render(scene, camera);
    }
    /*
        * Hier wordt de socketcommunicatie geregeld. Er wordt een nieuwe websocket aangemaakt voor het webadres dat we in
        * de server geconfigureerd hebben als connectiepunt (/connectToSimulation). Op de socket wordt een .onmessage
        * functie geregistreerd waarmee binnenkomende berichten worden afgehandeld.
        */
    socket = new WebSocket("ws://" + window.location.hostname + ":" + window.location.port + "/connectToSimulation");
    socket.onmessage = function (event) {
        //Hier wordt het commando dat vanuit de server wordt gegeven uit elkaar gehaald
        var command = parseCommand(event.data);

        //Wanneer het commando is "object_update", dan wordt deze code uitgevoerd. Bekijk ook de servercode om dit goed te begrijpen.
        if (command.command == "object_update") {
        //Wanneer het object dat moet worden geupdate nog niet bestaat (komt niet voor in de lijst met worldObjects op de client),
        //dan wordt het 3D model eerst aangemaakt in de 3D wereld.
        if (Object.keys(worldObjects).indexOf(command.parameters.uuid) < 0) {
            //Wanneer het object een robot is, wordt de code hieronder uitgevoerd.
            if (command.parameters.type == "robot") {
            let robot = robotCreator(robotMaterials);
            var group = new THREE.Group();
            group.add(robot);
            scene.add(group);
            worldObjects[command.parameters.uuid] = group;
            }
            if (command.parameters.type == "stellage") {
            worldObjects[command.parameters.uuid] = null;
            gltfLoader.load("./gltf/Stellage.glb", function (gltf) {
                let stellage = gltf.scene;
                let group = new THREE.Group();
                group.add(stellage);
                scene.add(group);
                worldObjects[command.parameters.uuid] = group;
            });
            }
            if (command.parameters.type == "warehouse") {
            worldObjects[command.parameters.uuid] = null;
            gltfLoader.load("./gltf/warehouse2.glb", function (gltf) {
                let warehouse = gltf.scene;
                warehouse.scale.x = 1.666;
                warehouse.scale.y = 1.4;
                warehouse.scale.z = 1.2;
                warehouse.position.x = 37;
                warehouse.position.z = 26.5;
                warehouse.rotation.y = 3.15;
                let group = new THREE.Group();
                group.add(warehouse);
                scene.add(group);
                worldObjects[command.parameters.uuid] = group;
            });
            }
            if (command.parameters.type == "truck") {
            worldObjects[command.parameters.uuid] = null;
            gltfLoader.load("./gltf/Truck.glb", function (gltf) {
                let truck = gltf.scene;
                truck.scale.x = 100;
                truck.scale.y = 100;
                truck.scale.z = 100;
                truck.position.x = -43.7;
                truck.position.y = -1.71;
                truck.position.z = 9.22;
                truck.rotation.y = 3.16;
                let group = new THREE.Group();
                group.add(truck);
                scene.add(group);
                worldObjects[command.parameters.uuid] = group;
            });
            }
        }
        var object = worldObjects[command.parameters.uuid];
        if (object != null) {
            object.position.x = command.parameters.x;
            object.position.y = command.parameters.y;
            object.position.z = command.parameters.z;
            object.rotation.x = command.parameters.rotationX;
            object.rotation.y = command.parameters.rotationY;
            object.rotation.z = command.parameters.rotationZ;
        }
        }
    };
    init();
    animate();
    }