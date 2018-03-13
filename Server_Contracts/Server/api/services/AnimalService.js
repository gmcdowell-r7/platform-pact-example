'use strict';

let animalDbMap = new Map();
animalDbMap.set("Tony", {
    id:"Tony",
    species:"Tiger",
    color:"Orange"
});
animalDbMap.set("Orville", {
    id:"Orville",
    species:"Duck",
    color:"Green"
});
animalDbMap.set("Elmo", {
    id:"Elmo",
    species:"Muppet",
    color:"Red"
});
animalDbMap.set("Dory", {
    id:"Dory",
    species:"Fish",
    color:"Blue"
});
animalDbMap.set("Ben", {
    id:"Ben",
    species:"Bear",
    color:"Brown"
});
animalDbMap.set("Ed", {
    id:"Ed",
    species:"Horse",
    color:"Black"
});
animalDbMap.set("Flipper", {
    id:"Flipper",
    species:"Dolphin",
    color:"Grey"
});
animalDbMap.set("Ryan", {
    id:"Ryan",
    species:"Velociraptor",
    color:"Ginger"
});

function getAnimal(id) {
    const animal = animalDbMap.get(id);
    return animal;
}

function getAllAnimals() {

    let animalsList = [];
    for (let key of animalDbMap){
        animalsList.push(key[1])
    }

    return animalsList;
}

function addAnimal(animal) {
    animalDbMap.set(animal.id, animal);
}

function deleteAnimal(id) {
    animalDbMap.delete(id);
}

module.exports = {
    getAnimal,
    getAllAnimals,
    addAnimal,
    deleteAnimal,
};