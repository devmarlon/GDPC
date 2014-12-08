package com.gardnerdenver.dao;


import com.gardnerdenver.model.PadraoManutencaoServico;

public class PadraoManutencaoServicoDao extends GenericGdpcDAO<PadraoManutencaoServico> {

    private static final long serialVersionUID = 1L;

    public PadraoManutencaoServicoDao() {
        super(null, PadraoManutencaoServico.class);
    }

//    public Person findPersonWithAllDogs(int personId) {
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("personId", personId);
//        
//        return super.findOneResult(Person.FIND_USER_BY_ID_WITH_DOGS, parameters);
//    }
    public void delete(PadraoManutencaoServico pds) {
        super.delete(pds.getPDS_ID(), PadraoManutencaoServico.class);
    }
}
