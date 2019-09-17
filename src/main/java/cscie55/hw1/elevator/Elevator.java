public class Elevator{

    private static final int NUMBEROFFLOORS=7;
    private int currentFloor;
    private boolean goingUp;
    private int floorCount;
    private int[] numberOfPassengersInAFloor;

    public Elevator() {
        this.currentFloor = 0;
        this.goingUp = true;
        this.numberOfPassengersInAFloor = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        this.floorCount = 1;
    }

    public void move(){

        /*
        This will change up the direction of movement
         */
        if(floorCount % 14 <= NUMBEROFFLOORS){
            setGoingUp(true);
        } else if(floorCount % 14 > NUMBEROFFLOORS){
            setGoingUp(false);
        }

        int passengersWhoLeftElevator;

        if(isGoingUp()){
            /*
            Sets the current floor
             */
            int floorGoingUp = floorCount % (NUMBEROFFLOORS + 1);
            setCurrentFloor(floorGoingUp);

            /*
            Calculates the number of passengers who left elevator at any given time
             */
            passengersWhoLeftElevator = -(this.numberOfPassengersInAFloor[getCurrentFloor()] -
                    this.numberOfPassengersInAFloor[getCurrentFloor() - 1]);

            /*
            When a passenger has left elevator, he is unoccupying the floors he was in before
             */
            resetNumberOfPassengers(passengersWhoLeftElevator, isGoingUp());
        }
        else {
            /*
            Sets the current floor
             */
            int floorGoingDown = NUMBEROFFLOORS - (floorCount % NUMBEROFFLOORS);
            setCurrentFloor(floorGoingDown);

            /*
            Calculates the number of passengers who left elevator at any given time
             */
            passengersWhoLeftElevator = -(this.numberOfPassengersInAFloor[getCurrentFloor()] -
                    this.numberOfPassengersInAFloor[getCurrentFloor() + 1]);

            /*
            When a passenger has left elevator, he is unoccupying the floors he was in before
             */
            resetNumberOfPassengers(passengersWhoLeftElevator, isGoingUp());
        }

        /*
        Empty elevator when it reaches full cycle
         */
        if(this.floorCount % 14 == 0){
            emptyElevator();
        }

        this.floorCount++;

        System.out.println(toString());

    }

    /*
    Method for unoccupying a floor previously occupied by a passenger
     */
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

    /*
    Increase the number of passengers by a given number upto destination
     */
    public void boardPassenger(int destinationFloor){
        if(isGoingUp()){
            incrementPassengersGoingUp(destinationFloor);
        }
        else {
            incrementPassengersGoingDown(destinationFloor);
        }
    }

    /*
    Incrementing passengers going up to their destination
     */
    private void incrementPassengersGoingUp(int destinationFloor){
        int floorsToGoUp = getCurrentFloor();

        while(floorsToGoUp < destinationFloor){
            this.numberOfPassengersInAFloor[floorsToGoUp] += 1;
            floorsToGoUp++;
        }
    }

    /*
    Incrementing passengers going down to their destination
     */
    private void incrementPassengersGoingDown(int destinationFloor){
        int floorsToGoDown = getCurrentFloor();

        while(floorsToGoDown > destinationFloor){
            this.numberOfPassengersInAFloor[floorsToGoDown] += 1;
            floorsToGoDown--;
        }
    }

    /*
    Empty the elevator when completing one cycle
     */
    public void emptyElevator(){
        for(int i = 0; i < NUMBEROFFLOORS; i++){
           this.numberOfPassengersInAFloor[i] = 0;
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