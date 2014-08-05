package game.SpawnGenerators;

/**
 *
 * @author aleš tichava
 */
public interface IGenerator {
    
    public void prolongTime();
    public void setSpawnTime(long spawnTime);
    public void spawn();
    
}
