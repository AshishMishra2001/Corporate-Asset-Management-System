package com.example.asset_management.dto;

import com.example.asset_management.model.Asset;
import com.example.asset_management.model.AssetStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetDTO {
    private Long id;
    private String assetName;
    private String serialNumber;
    private AssetStatus status;
    private Long employeeId;
    private String employeeName;

    public static AssetDTO fromEntity(Asset asset) {
        AssetDTO dto = new AssetDTO();
        dto.setId(asset.getId());
        dto.setAssetName(asset.getAssetName());
        dto.setSerialNumber(asset.getSerialNumber());
        dto.setStatus(asset.getStatus());
        
        if (asset.getEmployee() != null) {
            dto.setEmployeeId(asset.getEmployee().getId());
            dto.setEmployeeName(asset.getEmployee().getFullName());
        }
        
        return dto;
    }
}
