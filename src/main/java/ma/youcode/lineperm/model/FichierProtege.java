package ma.youcode.lineperm.model;

public class FichierProtege {

    private String nameOfFile;
    private String owner;
    private boolean ownerR;
    private boolean ownerW;
    private boolean ownerD;
    private boolean otherR;
    private boolean otherW;
    private boolean otherD;


    public FichierProtege(String name, String owner) {

        this.nameOfFile = name;
        this.owner = owner;
        this.ownerR = true;
        this.ownerW = true;
        this.ownerD = true;
        this.otherR = false;
        this.otherW = false;
        this.otherD = false;
    }


    public String getNameOfFile() {
        return this.nameOfFile;
    }

    public String getOwner() {
        return this.owner;
    }

    public boolean getOwnerR() {
        return this.ownerR;
    }
    public boolean getOwnerW() {
        return this.ownerW;
        
    }
    public boolean getOwnerD() {
        return this.ownerD;
    }
    public boolean getOtherR() {
        return this.otherR;
    }
    public boolean getOtherW() {
        return this.otherW;
    }
    public boolean getOtherD() {
        return this.otherD;
    }
    public void setotherR(boolean otherR) {
        this.otherR = otherR;
    }
    public void setotherW(boolean otherW) {
        this.otherW = otherW;
    }
    public void setotherD(boolean otherD) {
        this.otherD = otherD;
    }
}