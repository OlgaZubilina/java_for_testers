package model;

public record ContactData(String id,String firstname,String middlename,String lastname,String nickname,String title,String company,String address,String home,String mobile,String work,String fax, String email,String email2,String email3,String homepage) {

    public ContactData() {
        this("", "", "", "","","","","","","","","","","","","");
    }

    public ContactData withId (String id) {
        return new ContactData(id, this.firstname, this.middlename, this.lastname, this.nickname,
                this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(this.id, firstname, this.middlename, this.lastname, this.nickname,
                this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(this.id, this.firstname, this.middlename, lastname, this.nickname,
                this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withAdress(String adress) {
        return new ContactData(this.id,this.firstname, this.middlename, lastname, this.nickname,
                this.title, this.company, adress, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3, this.homepage);
    }
}