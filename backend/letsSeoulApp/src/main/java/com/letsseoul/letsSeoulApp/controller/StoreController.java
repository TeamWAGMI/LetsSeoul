package com.letsseoul.letsSeoulApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

      @GetMapping("{storeId}")
      public ResponseEntity<?>  storeInformationSearch(@PathVariable("storeId") Long storeId){

          return ResponseEntity.ok().body("1");
      }
}
