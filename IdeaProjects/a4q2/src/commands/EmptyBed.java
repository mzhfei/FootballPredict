//mengzhe fei
//11200913
//mef382
package commands;
import entities.Ward;

public class EmptyBed extends CommandStatus {
    /**]
     * print off all bed that is empty
     * @param ward
     */
    public void Empty(Ward ward){
        for ( int i = ward.getMinBedLabel(); i <= ward.getMaxBedLabel(); i++){
        if (!ward.isOccupied(i)){
            System.out.println("Bed" + i + "is empty \n");
        }
        }
        successful = true;
    }
}
