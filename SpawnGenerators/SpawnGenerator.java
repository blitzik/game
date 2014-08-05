package game.SpawnGenerators;

import game.ObjectRegister;


/**
 *
 * @author aleš tichava
 */
abstract public class SpawnGenerator {
    
    protected int spawnDuration;
    protected long spawnTime;
    protected int numberOfObjects;
    
    protected ObjectRegister or;
    
    public SpawnGenerator(int spawnDuration, int numberOfObjects, ObjectRegister or)
    {
        this.spawnDuration = spawnDuration;
        this.spawnTime = System.currentTimeMillis() + spawnDuration;
        this.or = or;        
        this.numberOfObjects = numberOfObjects;
    }
       
    public void prolongTime()
    {
        spawnTime = System.currentTimeMillis() + spawnDuration;
    }
    
    public void setSpawnTime(long spawnTime)
    {
        this.spawnTime = spawnTime;
    }
    
    abstract public void spawn();
    
}