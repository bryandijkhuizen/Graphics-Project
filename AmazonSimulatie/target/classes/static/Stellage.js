import * as THREE from "./three.js-master/build/three.module.js";
export default function createStellage(worldObjects, scene, gltfLoader, command) {
    gltfLoader.load("./gltf/Stellage.glb", function (gltf) {
        let stellage = gltf.scene;
        let group = new THREE.Group();
        group.add(stellage);
        scene.add(group);
        worldObjects[command.parameters.uuid] = group;        
    });
}
