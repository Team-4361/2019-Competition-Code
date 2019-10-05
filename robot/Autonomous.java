package frc.robot;

import frc.Library.Autonomous.AutonomousMethods;
import frc.Library.Chassis.TankDrive;
import frc.Library.Util.Counter;


public class Autonomous {
    Counter RunNum;
    boolean isNavx;
    AutonomousMethods theMethods;
    Intake theIntake;
    public Autonomous(TankDrive chassis, Intake theIntake)
    {
        isNavx = false;
        RunNum = new Counter();
        this.theIntake = theIntake;
        theMethods = new AutonomousMethods(RunNum, Constant.wheelDiameter*Math.PI, isNavx, chassis);
    }
    public void offHAB()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.offHAB, 1);
            theMethods.wait(.5);
        }
    }
    public void toLeftSide1FromLeftSide()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.sameSideToCargo1, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.cargoStopToCargoShip, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toLeftSide2FromLeftSide()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.sameSideToCargo2, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.cargoStopToCargoShip, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toLeftSide3FromLeftSide()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.sameSideToCargo3, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.cargoStopToCargoShip, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toRightSide1FromLeftSide()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.oppositeSideToMiddle, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(90, 1);
        } 
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.oppositeSideToMiddleCargo, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.oppositeSideToCargo1, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
        
    }
    public void toRightSide2FromLeftSide()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.oppositeSideToMiddle, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(90, 1);
        } 
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.oppositeSideToMiddleCargo, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.oppositeSideToCargo2, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toRightSide3FromLeftSide()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.oppositeSideToMiddle, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(90, 1);
        } 
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.oppositeSideToMiddleCargo, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.oppositeSideToCargo3, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toLeftMiddleFromLeftSide()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.middleToMiddle, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.middleToCargo, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.middleToMiddleCargoTurn2, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toRightMiddleFromLeftSide()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.middleToMiddle, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.oppositeSideToMiddleCargo, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.middleToMiddleCargoTurn2, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toLeftSide1FromRightSide()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.oppositeSideToMiddle, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(-90, 1);
        } 
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.oppositeSideToMiddleCargo, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.oppositeSideToCargo1, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toLeftSide2FromRightSide()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.oppositeSideToMiddle, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(-90, 1);
        } 
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.oppositeSideToMiddleCargo, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.oppositeSideToCargo2, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toLeftSide3FromRightSide()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.oppositeSideToMiddle, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(-90, 1);
        } 
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.oppositeSideToMiddleCargo, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.oppositeSideToCargo3, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toRightSide1FromRightSide()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.sameSideToCargo1, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.cargoStopToCargoShip, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toRightSide2FromRightSide()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.sameSideToCargo2, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.cargoStopToCargoShip, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toRightSide3FromRightSide()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.sameSideToCargo3, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.cargoStopToCargoShip, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toLeftMiddleFromRightSide()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.middleToMiddle, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.oppositeSideToMiddleCargo, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.middleToMiddleCargoTurn2, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }   
    }
    public void toRightMiddleFromRightSide()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.middleToMiddle, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.middleToCargo, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.middleToMiddleCargoTurn2, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toLeftSide1FromMiddle()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.firstTurnMid, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.secondTurnMid, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.thirdTurnMidToCargo1, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 6)
        {
            theMethods.goDistance(Constant.cargoStopToCargoShip, 1);
        }
        else if(RunNum.Get() == 7)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }

    }
    public void toLeftSide2FromMiddle()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.firstTurnMid, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.secondTurnMid, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.thirdTurnMidToCargo2, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 6)
        {
            theMethods.goDistance(Constant.cargoStopToCargoShip, 1);
        }
        else if(RunNum.Get() == 7)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toLeftSide3FromMiddle()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.firstTurnMid, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.secondTurnMid, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.thirdTurnMidToCargo3, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 6)
        {
            theMethods.goDistance(Constant.cargoStopToCargoShip, 1);
        }
        else if(RunNum.Get() == 7)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toMiddle1FromMiddle()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.middleToMiddle, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.middleToMiddleCargoTurn1, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.middleToMiddleCargoTurn2, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toMiddle2FromMiddle()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.middleToMiddle, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.middleToMiddleCargoTurn1, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.middleToMiddleCargoTurn2, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toRightSide1FromMiddle()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.firstTurnMid, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.secondTurnMid, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.thirdTurnMidToCargo1, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 6)
        {
            theMethods.goDistance(Constant.cargoStopToCargoShip, 1);
        }
        else if(RunNum.Get() == 7)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toRightSide2FromMiddle()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.firstTurnMid, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.secondTurnMid, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.thirdTurnMidToCargo2, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 6)
        {
            theMethods.goDistance(Constant.cargoStopToCargoShip, 1);
        }
        else if(RunNum.Get() == 7)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
    public void toRightSide3FromMiddle()
    {
        if(RunNum.Get() == 0)
        {
            theMethods.goDistance(Constant.firstTurnMid, 1);
        }
        else if(RunNum.Get() == 1)
        {
            theMethods.turn(90, 1);
        }
        else if(RunNum.Get() == 2)
        {
            theMethods.goDistance(Constant.secondTurnMid, 1);
        }
        else if(RunNum.Get() == 3)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 4)
        {
            theMethods.goDistance(Constant.thirdTurnMidToCargo3, 1);
        }
        else if(RunNum.Get() == 5)
        {
            theMethods.turn(-90, 1);
        }
        else if(RunNum.Get() == 6)
        {
            theMethods.goDistance(Constant.cargoStopToCargoShip, 1);
        }
        else if(RunNum.Get() == 7)
        {
            theIntake.closeIntake();
            theMethods.wait(.5);
        }
    }
}   