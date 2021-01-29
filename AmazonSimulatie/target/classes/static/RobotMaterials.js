import * as THREE from "./three.js-master/build/three.module.js";

const RobotMaterials = [
    new THREE.MeshBasicMaterial({map: new THREE.TextureLoader().load("textures/robot_side.png"),side: THREE.DoubleSide,}), //LEFT
    new THREE.MeshBasicMaterial({map: new THREE.TextureLoader().load("textures/robot_side.png"), side: THREE.DoubleSide,}), //RIGHT
    new THREE.MeshBasicMaterial({map: new THREE.TextureLoader().load("textures/robot_top.png"), side: THREE.DoubleSide,}), //TOP
    new THREE.MeshBasicMaterial({map: new THREE.TextureLoader().load("textures/robot_bottom.png"),side: THREE.DoubleSide,}), //BOTTOM
    new THREE.MeshBasicMaterial({map: new THREE.TextureLoader().load("textures/robot_front.png"),side: THREE.DoubleSide,}), //FRONT
    new THREE.MeshBasicMaterial({map: new THREE.TextureLoader().load("textures/robot_front.png"),side: THREE.DoubleSide,}), //BACK
];

export default RobotMaterials