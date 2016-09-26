package main.java.com.dao.gen;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "KEY".
 */
public class Key {

    private Long id;
    private String AesKey;
    private String PublicKey;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Key() {
    }

    public Key(Long id) {
        this.id = id;
    }

    public Key(Long id, String AesKey, String PublicKey) {
        this.id = id;
        this.AesKey = AesKey;
        this.PublicKey = PublicKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAesKey() {
        return AesKey;
    }

    public void setAesKey(String AesKey) {
        this.AesKey = AesKey;
    }

    public String getPublicKey() {
        return PublicKey;
    }

    public void setPublicKey(String PublicKey) {
        this.PublicKey = PublicKey;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}