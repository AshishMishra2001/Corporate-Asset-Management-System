package com.example.asset_management.service;

import com.example.asset_management.dto.AssetDTO;
import com.example.asset_management.model.Asset;
import com.example.asset_management.model.AssetStatus;
import com.example.asset_management.model.Employee;
import com.example.asset_management.repository.AssetRepository;
import com.example.asset_management.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetService {

    private final EmployeeRepository employeeRepository;
    private final AssetRepository assetRepository;

    public AssetService(EmployeeRepository employeeRepository, AssetRepository assetRepository) {
        this.employeeRepository = employeeRepository;
        this.assetRepository = assetRepository;
    }

    public Employee addEmployee(Employee e) {
        return employeeRepository.save(e);
    }

    public Asset addAsset(Asset a) {
        if (a.getStatus() == null) {
            a.setStatus(AssetStatus.AVAILABLE);
        }
        return assetRepository.save(a);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<AssetDTO> getAllAssetsAsDTO() {
        return assetRepository.findAll().stream()
                .map(AssetDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public Asset assignAssetToEmployee(Long assetId, Long employeeId) {
        Asset asset = assetRepository.findById(assetId).orElseThrow(() -> new RuntimeException("Asset not found"));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));

        asset.setEmployee(employee);
        asset.setStatus(AssetStatus.ASSIGNED);

        return assetRepository.save(asset);
    }

    @Transactional
    public Asset unassignAsset(Long assetId) {
        Asset asset = assetRepository.findById(assetId).orElseThrow(() -> new RuntimeException("Asset not found"));
        
        asset.setEmployee(null);
        asset.setStatus(AssetStatus.AVAILABLE);
        
        return assetRepository.save(asset);
    }
}
