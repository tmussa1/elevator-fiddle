public class Elevator{

    private static final int NUMBEROFFLOORS=7;
    private int currentFloor = 0;
    private boolean goingUp = true;
    private int floorCount = 1;
    private int[] numberOfPassengersInAFloor = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

    public void move(){

        //This will change up the direction of movement
        if(floorCount % 14 <= 7){
            setGoingUp(true);
        } else if(floorCount % 14 > 7){
            setGoingUp(false);
        }

        int passengersWhoLeftElevator;

        if(isGoingUp()){
            //Sets the current floor
            int floorGoingUp = floorCount % 8;
            setCurrentFloor(floorGoingUp);

            //Calculates the number of passengers who left elevator at any given time
            passengersWhoLeftElevator = -(this.numberOfPassengersInAFloor[getCurrentFloor()] -
                    this.numberOfPassengersInAFloor[getCurrentFloor() - 1]);

            //When a passenger has left elevator, he is unoccupying the floors he was in before
            resetNumberOfPassengers(passengersWhoLeftElevator, isGoingUp());
        }
        else {
            //Sets the current floor
            int floorGoingDown = 7 - (floorCount % 7);
            setCurrentFloor(floorGoingDown);

            //Calculates the number of passengers who left elevator at any given time
            passengersWhoLeftElevator = -(this.numberOfPassengersInAFloor[getCurrentFloor()] -
                    this.numberOfPassengersInAFloor[getCurrentFloor() + 1]);

            //When a passenger has left elevator, he is unoccupying the floors he was in before
            resetNumberOfPassengers(passengersWhoLeftElevator, isGoingUp());
        }

        this.floorCount++;

        System.out.println(toString());

    }

    //Method for unoccupying a floor previously occupied by a passenger
    private void resetNumberOfPassengers(int passengersWhoLeftElevator, boolean goingUp){
        if(goingUp){
            for(int i = 1; i < getCurrentFloor(); i++){
                this.numberOfPassengersInAFloor[i] -= passengersWhoLeftElevator;
            }
        } else {
            for(int l = NUMBEROFFLOORS - 1; l > getCurrentFloor(); l--){
                this.numberOfPassengersInAFloor[l] -= passengersWhoLeftElevator;
            }
        }
    }

    //Increase the number of passengers by a given number upto destination
    public void boardPassenger(int destinationFloor){
        if(isGoingUp()){
            incrementPassengersGoingUp(destinationFloor);
        }
        else {
            incrementPassengersGoingDown(destinationFloor);
        }
    }

    //Incrementing passengers going up to their destination
    private void incrementPassengersGoingUp(int destinationFloor){
        int floorsToGoUp = getCurrentFloor();

        while(floorsToGoUp < destinationFloor){
            this.numberOfPassengersInAFloor[floorsToGoUp] += 1;
            floorsToGoUp++;
        }
    }

    //Incrementing passengers going down to their destination
    private void incrementPassengersGoingDown(int destinationFloor){
        int floorsToGoDown = getCurrentFloor();

        while(floorsToGoDown > destinationFloor){
            this.numberOfPassengersInAFloor[floorsToGoDown] += 1;
            floorsToGoDown--;
        }
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public boolean isGoingUp() {
        return goingUp;
    }

    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Elevator{" +
                "Floor=" + (getCurrentFloor()) +
                ", With Number Of Passengers =" + this.numberOfPassengersInAFloor[getCurrentFloor()] +
                '}';
    }
}