package ru.unn.agile.SquareConverter;

public class SquareConverter {

    public static double toDistanationUnit(final double sourceUnit,
                                           final double distanationMultiplier) {
        if (sourceUnit < 0) {
            throw new IllegalArgumentException("Square must be positive number");
        }

        if (distanationMultiplier < 0) {
            throw new IllegalArgumentException("distanationMultiplier must be positive number");
        }

        return distanationMultiplier * sourceUnit;
    }

    public double toSqrKilometer(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, Constants.TO_KILOMETER_MULTIPLIER);
    }

    public double toHectare(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, Constants.TO_HECTARE_MULTIPLIER);
    }

    public double toAr(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, Constants.TO_AR_MULTIPLIER);
    }

    public double toSqrCentimeter(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, Constants.TO_SQR_SANTIMETER_MULTIPLIER);
    }

    public double toSqrMillimeter(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, Constants.TO_SQR_MILLIMETER_MULTIPLIER);
    }

    public double toSqrYard(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, Constants.TO_SQR_YARD_MULTIPLIER);
    }

    public double toSqrFoot(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, Constants.TO_SQR_FOOT_MULTIPLIER);
    }

    public double toSqrInch(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, Constants.TO_SQR_INCH_MULTIPLIER);
    }

    public double toSqrAcre(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, Constants.TO_SQR_ACRE_MULTIPLIER);
    }

    public double toSqrMile(final double sqrMeterCount) {
        return toDistanationUnit(sqrMeterCount, Constants.TO_SQR_MILE_MULTIPLIER);
    }

    public enum Cons {
        TO_KILOMETER_MULTIPLIER("TO_KILOMETER_MULTIPLIER") {
            public double toDistanationUnit(final double sqrMeterCount) {
                return sqrMeterCount * Constants.TO_KILOMETER_MULTIPLIER;
            }
        },

        TO_HECTARE_MULTIPLIER("TO_HECTARE_MULTIPLIER") {
            public double toDistanationUnit(final double sqrMeterCount) {
                return sqrMeterCount * Constants.TO_HECTARE_MULTIPLIER;
            }
        },
        TO_AR_MULTIPLIER("TO_AR_MULTIPLIER") {
            public double toDistanationUnit(final double sqrMeterCount) {
                return sqrMeterCount * Constants.TO_AR_MULTIPLIER;
            }
        },
        TO_SQR_SANTIMETER_MULTIPLIER("TO_SQR_SANTIMETER_MULTIPLIER") {
            public double toDistanationUnit(final double sqrMeterCount) {
                return sqrMeterCount * Constants.TO_SQR_SANTIMETER_MULTIPLIER;
            }
        },
        TO_SQR_MILLIMETER_MULTIPLIER("TO_SQR_MILLIMETER_MULTIPLIER") {
            public double toDistanationUnit(final double sqrMeterCount) {
                return sqrMeterCount * Constants.TO_SQR_MILLIMETER_MULTIPLIER;
            }
        },
        TO_SQR_YARD_MULTIPLIER("TO_SQR_YARD_MULTIPLIER") {
            public double toDistanationUnit(final double sqrMeterCount) {
                return sqrMeterCount * Constants.TO_SQR_YARD_MULTIPLIER;
            }
        },
        TO_SQR_FOOT_MULTIPLIER("TO_SQR_FOOT_MULTIPLIER") {
            public double toDistanationUnit(final double sqrMeterCount) {
                return sqrMeterCount * Constants.TO_SQR_FOOT_MULTIPLIER;
            }
        },
        TO_SQR_INCH_MULTIPLIER("TO_SQR_INCH_MULTIPLIER") {
            public double toDistanationUnit(final double sqrMeterCount) {
                return sqrMeterCount * Constants.TO_SQR_INCH_MULTIPLIER;
            }
        },
        TO_SQR_ACRE_MULTIPLIER("TO_SQR_ACRE_MULTIPLIER") {
            public double toDistanationUnit(final double sqrMeterCount) {
                return sqrMeterCount * Constants.TO_SQR_ACRE_MULTIPLIER;
            }
        },
        TO_SQR_MILE_MULTIPLIER("TO_SQR_MILE_MULTIPLIER") {
            public double toDistanationUnit(final double sqrMeterCount) {
                return sqrMeterCount * Constants.TO_SQR_MILE_MULTIPLIER;
            }
        };

        private final String name;
        Cons(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public abstract double toDistanationUnit(double sourceUnit);
    }

}
