package game.SpawnGenerators;

import game.ObjectRegister;
import java.util.HashMap;

/**
 *
 * @author aleš tichava
 */
public class ObjectGenerator {
    
    private HashMap<String, IGenerator> objects = null;
    
    public ObjectGenerator(ObjectRegister or)
    {
        this.objects = new HashMap<>();
        
        this.objects.put("DestructorGenerator", new DestructorGenerator(17000, 1, or));
        this.objects.put("SlicerGenerator", new SlicerGenerator(7500, 1, or));
    }
    
    public IGenerator getGenerator(String generatorName)
    {
        return this.objects.get(generatorName);
    }
    
    public void addGenerator(String generatorName, IGenerator generator)
    {
        this.objects.put(generatorName, generator);
    }
    
    public void spawnObjects()
    {
        for (HashMap.Entry<String, IGenerator> g : this.objects.entrySet()) {
            
            IGenerator generator = g.getValue();
            generator.spawn();
            
        }
    }
    
}