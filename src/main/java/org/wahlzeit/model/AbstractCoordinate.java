package org.wahlzeit.model;


public abstract class AbstractCoordinate implements Coordinate {

    protected final double EPSILON = 0.001;


    public double getCartesianDistance(Coordinate coordinate) throws IllegalStateException, NullPointerException {
        assertClassInvariants();
        assertNotNull(coordinate);
        return this.asCartesianCoordinate().doGetCartesianDistance(coordinate.asCartesianCoordinate());
    }

    public double getCentralAngle(Coordinate coordinate) throws IllegalStateException, NullPointerException {
        assertClassInvariants();
        assertNotNull(coordinate);
        return this.asSphericCoordinate().doGetCentralAngle(coordinate.asSphericCoordinate());
    }

    protected void assertNotNull(Object obj) throws NullPointerException {
        if(obj == null) {
            throw new NullPointerException("Argument is null");
        }
    }

    protected void assertClassInvariants() throws IllegalStateException {
        assert true;
    }

    /**
     * cartesian coordinates in database, thus isEqual() can be handled
     * in parent class AbstractCoordinate
     */
    public boolean isEqual(Coordinate coordinate) throws IllegalStateException, NullPointerException {
        assertClassInvariants();
        assertNotNull(coordinate);
        CartesianCoordinate from = this.asCartesianCoordinate();
        CartesianCoordinate to = coordinate.asCartesianCoordinate();

        boolean eqX = Math.abs(from.getX() - to.getX()) <= EPSILON;
        boolean eqY = Math.abs(from.getY() - to.getY()) <= EPSILON;
        boolean eqZ = Math.abs(from.getZ() - to.getZ()) <= EPSILON;

        return eqX && eqY && eqZ;
    }

}
