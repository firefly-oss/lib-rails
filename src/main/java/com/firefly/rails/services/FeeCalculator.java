/*
 * Copyright 2025 Firefly Software Solutions Inc
 */

package com.firefly.rails.services;

import com.firefly.rails.domain.*;
import com.firefly.rails.dtos.fees.*;
import reactor.core.publisher.Mono;

/**
 * Service interface for fee calculation across different rails.
 * 
 * Each rail implementation should provide its own fee calculation logic.
 */
public interface FeeCalculator {

    /**
     * Calculate fees for a payment.
     *
     * @param request fee calculation request
     * @return reactive publisher with calculated fees
     */
    Mono<FeeCalculationResult> calculateFees(FeeCalculationRequest request);

    /**
     * Get fee structure for a rail.
     * Returns the fee schedule/tiers for the rail.
     *
     * @return reactive publisher with fee structure
     */
    Mono<FeeStructure> getFeeStructure();

    /**
     * Compare fees across multiple settlement speeds.
     *
     * @param request fee comparison request
     * @return reactive publisher with fee comparison
     */
    Mono<FeeComparisonResult> compareFees(FeeComparisonRequest request);

    /**
     * Estimate foreign exchange fees (if applicable).
     *
     * @param request FX fee request
     * @return reactive publisher with FX fees
     */
    Mono<FXFeeResult> calculateFXFees(FXFeeRequest request);
}
