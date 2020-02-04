package br.com.otta.bank.client.model;

import java.util.Arrays;

/**
 * Enum para listar quais são os tipos possíveis para um cliente.
 * 
 * @author Guilherme
 *
 */
public enum ClientType {
    PHYSICAL(0),
    LEGAL(1);

    private int id;

    private ClientType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ClientType getClientType(int id) {
        return Arrays.stream(ClientType.values())
                .filter((type) -> type.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Could not find any ClientTYpe with ID provided."));
    }
}
