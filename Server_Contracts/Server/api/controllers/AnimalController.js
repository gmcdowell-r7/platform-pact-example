'use strict';

const animalService = require('../services/AnimalService');

function get(req, res) {

    const id = req.swagger.params.id.value;

    let animal = animalService.getAnimal(id);
    if (!animal) return res.status(404).json(animal);

    return res.status(200).json(animal);

}

function getAll(req, res) {
    
    let animals = animalService.getAllAnimals();

    return res.status(200).json(animals);
}

function post(req, res) {
    
    let animal = req.body;

    animalService.addAnimal(animal);

    return res.status(201).json(animal);
}

function deleteAnimal(req, res) {
    
    const id = req.swagger.params.id.value;
    
    let animal = animalService.deleteAnimal(id);
    
    return res.status(204).json(animal);
    
}

module.exports = {
    get,
    getAll,
    post,
    deleteAnimal,
};