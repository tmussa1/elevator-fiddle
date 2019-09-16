public class ElevatorTest{

    public static void main(String [] args){

        Elevator elevator = new Elevator();
        elevator.boardPassenger(3);
        elevator.boardPassenger(3);
        elevator.boardPassenger(5);

        for(int i = 0; i < 13; i++){
            elevator.move();
        }

        elevator.toString();
    }
}