package com.gardnerdenver.dao;

import com.gardnerdenver.model.PadraoManutencaoPeca;

public class PadraoManutencaoPecaDao extends GenericGdpcDAO<PadraoManutencaoPeca> {

    private static final long serialVersionUID = 1L;

    public PadraoManutencaoPecaDao() {
        super(null, PadraoManutencaoPeca.class);
    }

//    public Person findPersonWithAllDogs(int personId) {
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("personId", personId);
//        
//        return super.findOneResult(Person.FIND_USER_BY_ID_WITH_DOGS, parameters);
//    }
    public void delete(PadraoManutencaoPeca pmp) {
        super.delete(pmp.getPMP_ID());
    }
}
