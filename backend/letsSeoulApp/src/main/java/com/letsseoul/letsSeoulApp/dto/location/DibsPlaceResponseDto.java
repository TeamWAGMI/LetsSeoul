package com.letsseoul.letsSeoulApp.dto.location;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DibsPlaceResponseDto {

    private final Long storeId;
    private final String storeName;
    private final Long reviewCount;
    private final String latitude;
    private final String longitude;
}
