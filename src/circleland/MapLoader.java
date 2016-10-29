/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;
import circleland.Display.Camera;
import circleland.Items.Portal;
import circleland.NPCs.Alaira;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * @author Jeff
 */
public class MapLoader {
    
    public MapLoader(){
        
    }
    public CircleMap enterMap(String mapName, Camera c, CircleMouse mouse, CircleClass pl){
        CircleMap cM = loadMap(mapName);
        cM.camera(c);
        cM.mouse(mouse);
        cM.player(pl);
        return cM;
    }
    public CircleMap loadMap(String mapName){
        //load an xml file and parse
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(mapName));
            
            //get document element and glean details from
            //set up necessary data holders
            Element documentElement = document.getDocumentElement();
            CircleMap newMap  = mapXML(documentElement);
            newMap.name(mapName);
            
            ArrayList<String> rawLayers = new ArrayList<>();
            ArrayList<ObjectData> mapObjects = new ArrayList<>();
            //System.out.println("w:" + width + " h:" + height + " tw:" + tileWidth + " th:" + tileHeight);
            NodeList nodeList = document.getDocumentElement().getChildNodes();
            
            //iterate over all top nodes
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    //System.out.println("elem name= " + elem.getTagName());
                    //Add Tileset information
                    if(elem.getTagName().equalsIgnoreCase("tileset")){
                        Element imageElement = (Element)elem.getElementsByTagName("image").item(0);
                        newMap.spriteSheet(new SpriteSheet(
                                imageElement.getAttribute("source"),newMap.tileWidth,newMap.tileHeight));
                    }
                    //Add layer data
                    if(elem.getTagName().equalsIgnoreCase("layer")){
                        Element dataElement = (Element)elem.getElementsByTagName("data").item(0);
                        String rawLayerData = dataElement.getTextContent();
                        System.out.println(rawLayerData);
                        rawLayers.add(rawLayerData);
                    }
                    if(elem.getTagName().equalsIgnoreCase("objectGroup")){
                        NodeList objects = elem.getElementsByTagName("object");
                        for(int j = 0; j < objects.getLength(); j++)
                        {
                            Element e = (Element)objects.item(j);
                            String type = e.getAttribute("type");
                            String name = e.getAttribute("name");
                            double xPos = Double.parseDouble(e.getAttribute("x"));
                            double yPos = Double.parseDouble(e.getAttribute("y"));
                            mapObjects.add(new ObjectData(name,type,(int)xPos,(int)yPos));
                        }
                    }
                }
            }
            fillMapData(newMap,rawLayers);
            instantiateObjects(newMap, mapObjects);
            return newMap;
        }catch(Exception e){
            System.out.println("Error loading xml file: " + mapName);
            System.out.println(e.getMessage());
        }
        return null;
    }
    public CircleMap mapXML(Element e){
        int width = Integer.parseInt(e.getAttribute("width"));
        int height = Integer.parseInt(e.getAttribute("height"));
        int tileWidth = Integer.parseInt(e.getAttribute("tilewidth"));
        int tileHeight = Integer.parseInt(e.getAttribute("tileheight"));
        CircleMap cM = new CircleMap(width,height);
        cM.tileHeight(tileHeight);
        cM.tileWidth(tileWidth);
        cM.mapBounds().width = width*tileWidth;
        cM.mapBounds().height = height*tileHeight;
        return cM;
    }
    public void fillMapData(CircleMap cM, ArrayList<String> rawLayers){
            //The first row of each rawlayer is blank. therefore the y-1 subscript
            //when putting the value into the array
        int[][][] mapData = new int[cM.width][cM.height][rawLayers.size()];
        ArrayList<RectangleObject> collidables = new ArrayList<>();
        for(int z = 0; z<rawLayers.size();z++){
            String[] tileRows = rawLayers.get(z).split("[\\r\\n]+");
            for(int y = 0; y < tileRows.length; y++){
                if(tileRows[y].equalsIgnoreCase(""))
                {
                    continue;
                }
                //System.out.println("Row" + y+ " = " + tileRows[y]);
                String[] tileColumns = tileRows[y].split(",");
                for(int x = 0; x < tileColumns.length; x++){
                    //System.out.println("x = " + Integer.parseInt(tileColumns[x]));
                    mapData[x][y-1][z] = Integer.parseInt(tileColumns[x]);
                }
            }
        }
        for(int i = 0; i < mapData.length; i++){
            for(int j = 0; j < mapData[0].length; j++){
                for(int k = 0; k < mapData[0][0].length;k++){
                    if(k == 3)//make this the collidable mask
                    {
                        if(mapData[i][j][k] != 0)
                            collidables.add(new RectangleObject("",i*cM.tileWidth,j*cM.tileHeight,cM.tileWidth,cM.tileHeight));
                    }
                }
            }
        }
        cM.tileArray(mapData);
        cM.mapCollision().addAll(collidables);
    }
    public void instantiateObjects(CircleMap cM, ArrayList<ObjectData> mapObjects){
        for(ObjectData object : mapObjects){
            if(object.type().equalsIgnoreCase("NPC")){
                if(object.name().equalsIgnoreCase("Alaira")){
                    Alaira npc = new Alaira();
                    npc.staticPosition().x = object.xPos();
                    npc.staticPosition().y = object.yPos();
                    cM.players().add(npc);
                }
            }
            if(object.type().equalsIgnoreCase("Portal")){
                    Portal p = new Portal(object.name(), object.xPos(),object.yPos());
                    cM.itemsOnGround().add(p);
            }
            
        }
    }
}   

class ObjectData{
    protected int xPos;
    public int xPos(){return xPos;}
    public void xPos(int l){xPos = l;}
    protected int yPos;
    public int yPos(){return yPos;}
    public void yPos(int l){yPos = l;}
    protected String name;
    public String name(){return name;}
    public void name(String l){name = l;}
    protected String type;
    public String type(){return type;}
    public void type(String l){type = l;}
    
    public ObjectData(String name, String type, int x, int y){
        this.name = name;
        this.type = type;
        this.xPos = x;
        this.yPos = y;
    }
    
}