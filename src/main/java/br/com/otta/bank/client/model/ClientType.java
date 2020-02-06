package br.com.otta.bank.client.model;

import java.util.Arrays;

/**
 * Enum para listar quais são os tipos possíveis para um cliente.
 * 
 * @author Guilherme
 *
 */
public enum ClientType {
    PHYSICAL(0, "PF"),
    LEGAL(1, "PJ");

    private int id;
    private String label;

    private ClientType(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static ClientType getClientType(int id) {
        return Arrays.stream(ClientType.values())
                .filter((type) -> type.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Could not find any ClientType with ID provided."));
    }
}
