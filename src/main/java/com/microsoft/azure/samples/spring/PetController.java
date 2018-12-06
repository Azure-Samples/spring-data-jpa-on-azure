/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */

package com.microsoft.azure.samples.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping(path="/pets")
public class PetController {
    @Autowired
    private PetRepository petRepository;

    @PostMapping
    public @ResponseBody String createPet(@RequestBody Pet pet) {
        petRepository.save(pet);
        return String.format("Added %s.", pet);
    }

    @GetMapping
    public @ResponseBody Iterable<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Optional<Pet> getPet(@PathVariable Integer id) {
        return petRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deletePet(@PathVariable Integer id) {
        petRepository.deleteById(id);
        return "Deleted " + id;
    }
}
