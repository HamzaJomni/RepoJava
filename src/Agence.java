import java.util.*;
public class Agence {
    private String nom;
    private ListVoitures vs;
    private Map<Client, ListVoitures> ClientVoitureLoue;

    public Agence(String nom, ListVoitures listAgence, Map<Client, ListVoitures> clientVoitureLoue) {
        this.nom = nom;
        this.vs = listAgence;
        this.ClientVoitureLoue = clientVoitureLoue;
    }
    public void ajoutVoiture(Voiture v) throws VoitureException {
        vs.ajoutVoiture(v);
    }
    public void suppVoiture(Voiture v)throws VoitureException{
        vs.supprimeVoiture(v);
    }

    public void loueClientVoiture(Client cl, Voiture v) throws VoitureException {
        if (ClientVoitureLoue.containsKey(cl)) {
            ListVoitures listClient = ClientVoitureLoue.get(cl);
            listClient.ajoutVoiture(v);
            this.vs.supprimeVoiture(v);
            this.ClientVoitureLoue.put(cl, listClient);
        } else {
            ListVoitures list = new ListVoitures();
            list.ajoutVoiture(v);
            this.vs.supprimeVoiture(v);
            this.ClientVoitureLoue.put(cl, list);
        }
    }
    public void retourClientVoiture(Client cl, Voiture v) {
        if (ClientVoitureLoue.get(cl).getVoitures().contains(v)) {
            ClientVoitureLoue.get(cl).supprimeVoiture(v);
            vs.ajoutVoiture(v);
        } else {
            System.out.println("ce client n'a pas louer cette voiture");
        }
    }

    public List<Voiture> selectVoitureSelonCritere(Critere c){
        //à completer
    }
    public Set<Client> ensembleClientsLoueurs(){
        return this.ClientVoitureLoue.keySet();
    }
    public Collection<ListVoitures> collectionVoituresLouees(){
        return this.ClientVoitureLoue.values();
    }
    public void afficheLesClientsEtLeursListesVoitures(){
        this.ClientVoitureLoue.forEach(((client, listVoitures) -> {
            System.out.println("Client" + client);
            listVoitures.affiche();
        }));
    }
    public Map<Client, ListVoitures>
    triCodeCroissant(){
        TreeMap<Client, ListVoitures> triMap = new TreeMap<>(new TriCodeCroissant());
        triMap.putAll(ClientVoitureLoue);
        return triMap;
    }
    public Map<Client, ListVoitures> triNomCroissant(){
        TreeMap<Client, ListVoitures> triMap = new TreeMap<>(new TriNomCroissant());
        triMap.putAll(ClientVoitureLoue);
        return triMap;
    }
}