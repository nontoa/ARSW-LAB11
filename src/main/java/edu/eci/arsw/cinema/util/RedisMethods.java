package edu.eci.arsw.cinema.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import edu.eci.arsw.cinema.entities.CinemaFunction;
import edu.eci.arsw.cinema.repository.CinemaException;

@Component
public class RedisMethods {
    
    public static void saveToREDIS(String key, String data) {
        Jedis jedis = JedisUtil.getPool().getResource();
        jedis.watch(key);
        Transaction t1 = jedis.multi();
        t1.set(key, data);
        t1.exec();
        jedis.close();
    }

    public static String getFromREDIS(String key) {
        boolean intentar = true;
        String content = "";
        while (intentar) {
            Jedis jedis = JedisUtil.getPool().getResource(); // Inicializar jedis y obtener recursos
            jedis.watch(key); // Hacer watch de la llave
            Transaction t = jedis.multi();// Crear la transacción t
            Response<String> data = t.get(key);
            List<Object> result = t.exec();
            if (result.size() > 0) {
                intentar = false;
                content = data.get();
                jedis.close();// Cerrar recurso jedis
            }
        }
        return content;
    }

    public static void main(String[] args) {
        saveToREDIS("nuevo", "This is a new Value");
        System.out.println(getFromREDIS("nuevo"));
    }

}
