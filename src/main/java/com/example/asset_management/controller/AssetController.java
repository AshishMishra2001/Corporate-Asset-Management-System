package com.example.asset_management.controller;

import com.example.asset_management.dto.AssetDTO;
import com.example.asset_management.model.Asset;
import com.example.asset_management.model.Employee;
import com.example.asset_management.service.AssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee saved = assetService.addEmployee(employee);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> listEmployees() {
        return ResponseEntity.ok(assetService.getAllEmployees());
    }

    @PostMapping("/assets")
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
        Asset saved = assetService.addAsset(asset);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/assets")
    public ResponseEntity<List<AssetDTO>> listAssets() {
        return ResponseEntity.ok(assetService.getAllAssetsAsDTO());
    }

    @PutMapping("/assets/{assetId}/assign/{employeeId}")
    public ResponseEntity<Asset> assignAsset(@PathVariable Long assetId, @PathVariable Long employeeId) {
        Asset updated = assetService.assignAssetToEmployee(assetId, employeeId);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/assets/{assetId}/unassign")
    public ResponseEntity<Asset> unassignAsset(@PathVariable Long assetId) {
        Asset updated = assetService.unassignAsset(assetId);
        return ResponseEntity.ok(updated);
    }
}
