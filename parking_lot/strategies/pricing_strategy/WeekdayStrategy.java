package parking_lot.strategies.pricing_strategy;

import parking_lot.models.VehicleType;
import parking_lot.utils.DateTimeUtils;

import java.util.Date;

public class WeekdayStrategy implements CalculateFeesStrategy{
    @Override
    public double calculateFees(Date entryTime, Date exitTime, VehicleType vehicleType) {
        return DateTimeUtils.calcHours(entryTime, exitTime) * 10;
    }
}
