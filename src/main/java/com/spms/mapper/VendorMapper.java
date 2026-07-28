package com.spms.mapper;

import com.spms.app.entity.Vendor;
import com.spms.dto.request.VendorRequestDTO;
import com.spms.dto.response.VendorResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

// Converts between Vendor entity and DTOs
@Mapper(componentModel = "spring")
public interface VendorMapper {

    // Request DTO -> Entity (for create)
    // Ignore fields that are set by system/service
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Vendor toEntity(VendorRequestDTO requestDTO);

    // Entity -> Response DTO
    VendorResponseDTO toResponseDTO(Vendor vendor);

    // List of entities -> list of response DTOs
    List<VendorResponseDTO> toResponseDTOList(List<Vendor> vendors);

    // Update existing entity from request DTO
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDTO(VendorRequestDTO requestDTO, @MappingTarget Vendor vendor);
}