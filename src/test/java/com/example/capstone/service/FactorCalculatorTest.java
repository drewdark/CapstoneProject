package com.example.capstone.service;

import com.example.capstone.model.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

public class FactorCalculatorTest {

    @Test
    public void testCalculateVehicleTypeFactor(){

        FactorCalculator myCalculator = new FactorCalculator();

        assertEquals(1.6, myCalculator.calculateVehicleTypeFactor("Hatchback"), 0.0);
        assertEquals(1.3, myCalculator.calculateVehicleTypeFactor("Cabriolet"), 0.0);
        assertEquals(1.4, myCalculator.calculateVehicleTypeFactor("Coupe"), 0.0);
        assertEquals(1.5, myCalculator.calculateVehicleTypeFactor("Estate"), 0.0);
        assertEquals(1.7, myCalculator.calculateVehicleTypeFactor("Other"), 0.0);
    }

    @Test
    public void testCalculateVehicleTypeFactorDefault(){

        FactorCalculator myCalculator = new FactorCalculator();

        assertNotEquals(1.6, myCalculator.calculateVehicleTypeFactor("Hatchbackk"), 0.0);

    }


    @Test
    public void testCalculateEngineSizeFactor(){

        FactorCalculator myCalculator = new FactorCalculator();

        assertEquals(2.5, myCalculator.calculateEngineSizeFactor("2500"), 0.0);
        assertEquals(1.0, myCalculator.calculateEngineSizeFactor("1000"), 0.0);
        assertEquals(1.6, myCalculator.calculateEngineSizeFactor("1600"), 0.0);
        assertEquals(2.0, myCalculator.calculateEngineSizeFactor("2000"), 0.0);
        assertEquals(3.0, myCalculator.calculateEngineSizeFactor("3000"), 0.0);
        assertEquals(3.5, myCalculator.calculateEngineSizeFactor("Other"), 0.0);

    }

    @Test
    public void testCalculateEngineSizeFactorDefault(){

        FactorCalculator myCalculator = new FactorCalculator();

        assertNotEquals(2.5, myCalculator.calculateVehicleTypeFactor("25000"), 0.0);

    }

    @Test
    public void testCalculateVehicleValueFactor(){

        FactorCalculator myCalculator = new FactorCalculator();

        assertEquals(1.0, myCalculator.calculateVehicleValueFactor("2000"), 0.0);
        assertEquals(1.2, myCalculator.calculateVehicleValueFactor("6000"), 0.0);

    }

    @Test
    public void testCalculateAdditionalDriversFactor(){

        FactorCalculator myCalculator = new FactorCalculator();

        assertEquals(1.1, myCalculator.calculateAdditionalDriversFactor("1"), 0.0);
        assertEquals(1.2, myCalculator.calculateAdditionalDriversFactor("2"), 0.0);

    }

    @Test
    public void testCalculateCommercialUseFactor(){

        FactorCalculator myCalculator = new FactorCalculator();

        assertEquals(1.1, myCalculator.calculateCommercialUseFactor("true"), 0.0);
        assertEquals(1.0, myCalculator.calculateCommercialUseFactor("false"), 0.0);

    }

    @Test
    public void testCalculateOutsideStateUseFactor(){

        FactorCalculator myCalculator = new FactorCalculator();

        assertEquals(1.1, myCalculator.calculateOutsideStateUseFactor("true"), 0.0);
        assertEquals(1.0, myCalculator.calculateOutsideStateUseFactor("false"), 0.0);

    }


    @Test
    public void testCalculateInsuranceQuote(){

        // setup driver
        String firstName = "Andrew";
        String lastName = "Dark";
        Long id = 1L;
        String prefix = "Mr";
        String telNumber= "12345678910";
        String address1 = "123";
        String address2 = "Something Road";
        String city = "Belfast";
        String postCode = "BT123";
        String carType = "Cabriolet";
        String engineSize = "1600";
        String additionalDrivers = "1";
        String commercialPurposes = "true";
        String outsideState = "true";
        String dateRegistered = "10-02-2021";
        String vehicleValue = "6000";
        String comments = ":)";
        Request driver = new Request(id, prefix, firstName, lastName,  telNumber, address1, address2, city, postCode, carType, engineSize, additionalDrivers, commercialPurposes, outsideState, vehicleValue, dateRegistered, comments);


        FactorCalculator myCalculator = new FactorCalculator();


        assertEquals("332.22", myCalculator.calculateInsuranceQuote(driver));
    }

}