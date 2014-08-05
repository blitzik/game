package game.SpawnGenerators;

import game.ObjectRegister;
import game.Objects.Slicer;

/**
 *
 * @author aleš tichava
 */
public class SlicerGenerator extends SpawnGenerator implements IGenerator{
    
    public SlicerGenerator(int spawnDuration, int numberOfObjects, ObjectRegister or)
    {
        super(spawnDuration, numberOfObjects, or);
    }
    
    @Override
    public void spawn()
    {
        if (spawnTime < System.currentTimeMillis()) {
            
            for (int i = 1; i <= numberOfObjects; i++) {
                this.or.addGameObject(new Slicer());
            }
            
            prolongTime();
        }
    }
    
}
