package gameClient;
import api.*;
//import api.edge_data;
import gameClient.util.Point3D;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {
    private FunctionForTests _func = new FunctionForTests();
    private String[] _array = _func.getArrayOfScenariosPath();
    private List<Pokemon> _pokemonsList;
    private String _fileName = "/GamePokemons.json";
    Pokemon _pokemon ;


    @Test
    void constructor() {
        for (String str : _array){
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for(Pokemon p : _pokemonsList){
                Pokemon _pokemon = new Pokemon(p.getLocation(),p.getType(),p.getValue(),0 , p.get_edge());
                assertEquals(_pokemon.getType() , p.getType());
                assertEquals(_pokemon.getValue() , p.getValue());
                assertEquals(_pokemon.get_edge() , p.get_edge());
                assertEquals(_pokemon.getLocation() , p.getLocation());
            }
        }
    }

    @Test
    void get_edge() {
        for (String str : _array){
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for(Pokemon p : _pokemonsList){
                Pokemon _pokemon = new Pokemon(p.getLocation(),p.getType(),p.getValue(),0 , p.get_edge());
                assertEquals(_pokemon.get_edge() , p.get_edge());
                EdgeData e = new EdgeData(Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE);
                assertNotEquals(p.get_edge(), e);
            }
        }
    }

    @Test
    void set_edge() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                edge_data _edge = new EdgeData(Integer.MAX_VALUE, Integer.MAX_VALUE,Integer.MAX_VALUE);
                p.set_edge(_edge);
                assertEquals(p.get_edge(), _edge);
                _edge = new EdgeData(Integer.MIN_VALUE, Integer.MIN_VALUE,Integer.MIN_VALUE);
                assertNotEquals(p.get_edge(), _edge);
            }
        }
    }

    @Test
    void getLocation() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                Point3D point = new Point3D(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);
                p.setLocation(point);
                assertEquals(p.getLocation(), point);
                point = new Point3D(Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE);
                assertNotEquals(p.getLocation(), point);
            }
        }
    }

    @Test
    void getType() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                p.setType(Integer.MAX_VALUE);
                assertEquals(p.getType(), Integer.MAX_VALUE);
                assertNotEquals(p.getType(), Integer.MIN_VALUE);
            }
        }
    }

    @Test
    void getValue() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                p.setValue(Integer.MAX_VALUE);
                assertEquals(p.getValue(), Integer.MAX_VALUE);
                assertNotEquals(p.getValue(), Integer.MIN_VALUE);
            }
        }
    }

    @Test
    void getMin_dist() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                p.setMin_dist(Integer.MAX_VALUE);
                assertEquals(p.getMin_dist(), Integer.MAX_VALUE);
                assertNotEquals(p.getMin_dist(), Integer.MIN_VALUE);
            }
        }
    }

    @Test
    void setMin_dist() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                p.setMin_dist(Integer.MIN_VALUE);
                assertEquals(p.getMin_dist(), Integer.MIN_VALUE);
                assertNotEquals(p.getMin_dist(), Integer.MAX_VALUE);

            }
        }
    }

    @Test
    void getFrom() {
     for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                p.setFrom(Integer.MAX_VALUE);
                assertEquals(p.getFrom(), Integer.MAX_VALUE);
                assertNotEquals(p.getFrom(), Integer.MIN_VALUE);
            }
        }
    }

    @Test
    void setFrom() {
            for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                p.setType(Integer.MAX_VALUE);
                assertNotEquals(p.getType(), -100);
                assertEquals(p.getType(), Integer.MAX_VALUE);
            }
        }
    }

    @Test
    void getTo() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                p.setTo(Integer.MAX_VALUE);
                assertEquals(p.getTo(), Integer.MAX_VALUE);
                assertNotEquals(p.getTo(), Integer.MIN_VALUE);
            }
        }
    }

    @Test
    void setTo() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                p.setTo(Integer.MAX_VALUE);
                assertEquals(p.getTo(), Integer.MAX_VALUE);
                assertNotEquals(p.getTo(), Integer.MIN_VALUE);
            }
        }
    }

    @Test
    void getWorth() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                p.setWorth(Integer.MAX_VALUE);
                assertEquals(p.getWorth(), Integer.MAX_VALUE);
            }
        }
    }

    @Test
    void setWorth() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                p.setWorth(Integer.MAX_VALUE);
                assertNotEquals(p.getWorth(), Integer.MIN_VALUE);
                assertEquals(p.getWorth(), Integer.MAX_VALUE);
            }
        }
    }

    @Test
    void testToString() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            for (Pokemon p : _pokemonsList) {
                str = "Pokemon{" +
                        "_edge=" + p.get_edge() +
                        ", _value=" + p.getValue() +
                        ", _type=" + p.getType() +
                        ", _pos=" + p.getLocation() +
                        ", min_dist=" + p.getMin_dist() +
                        ", min_ro=" + p.getFrom() +
                        '}';
                ;
                assertEquals(p.toString(), str);
                assertNotEquals(p.toString(), str+ "+");
            }
        }
    }

    @Test
    void testEquals() {
        for (String str : _array) {
            String json = _func.readJsonFromFileAndGetAsString(str + _fileName);
            _pokemonsList = _func.json2Pokemons(json);
            Point3D point = new Point3D(Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE);
            _pokemon = new Pokemon(point,100,Integer.MIN_VALUE,Integer.MIN_VALUE,new EdgeData(-10,-10,0.0));
            for (Pokemon p : _pokemonsList) {
                Pokemon p2 = new Pokemon(p.getLocation(),p.getType(),p.getValue(),0.0,p.get_edge());
                assertTrue(p.equals(p2));
                assertFalse(p.equals(_pokemon));
            }
        }
    }
}