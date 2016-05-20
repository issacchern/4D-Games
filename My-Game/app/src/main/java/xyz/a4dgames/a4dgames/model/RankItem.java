package xyz.a4dgames.a4dgames.model;

/**
 * Created by Issac on 5/19/2016.
 */
public class RankItem {

    private int rank;
    private String username;
    private String cash;
    private String imageUrl;

    public RankItem(int rank, String username, String cash, String imageUrl){
        this.rank = rank;
        this.username = username;
        this.cash = cash;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }
}
