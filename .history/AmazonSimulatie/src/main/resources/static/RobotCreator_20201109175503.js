import * as THREE from "./three.js-master/build/three.module.js";

export default function robotCreator(cubeMaterials) {
    let geometry = new THREE.BoxGeometry(0.9, 0.3, 0.9);
    let material = new THREE.MeshFaceMaterial(cubeMaterials);
    let robot = new THREE.Mesh(geometry, material);
    robot.position.y = 0.15;
    robot.scale.x = 3;
    robot.scale.y = 3;
    robot.scale.z = 3;
    return robot;
}
