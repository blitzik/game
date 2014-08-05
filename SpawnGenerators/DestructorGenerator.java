package game.SpawnGenerators;

import game.ObjectRegister;
import game.Objects.Destructor;

/**
 *
 * @author aleš tichava
 */
public class DestructorGenerator extends SpawnGenerator implements IGenerator{
        
    
    public DestructorGenerator(int spawnDuration, int numberOfObjects, ObjectRegister or)
    {
        super(spawnDuration, numberOfObjects, or);
    }
    
    @Override
    public void spawn()
    {
        if (spawnTime != 0) {
        
            if (spawnTime < System.currentTimeMillis()) {
            
                for (int i = 1; i <= numberOfObjects; i++) {
                    this.or.addGameObject(new Destructor());
                }
                
                // Nebude se objevovat dalsi, dokud se nesebere
                // ten aktualni (negeneruje se kazdych x sekund)
                spawnTime = 0;
            
            }
            
        }
        
    }
    
}