import * as THREE from "./three.js-master/build/three.module.js";
export default function createTruck(worldObjects, scene, gltfLoader, command) {
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
