import * as THREE from "./three.js-master/build/three.module.js";
export default function createWarehouse(worldObjects, scene, gltfLoader, command) {
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