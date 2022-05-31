package ba.reservation.hotelmanagement.business.service;

public enum PrivilegeServiceFactory {
    PRIVILEGE_SERVICE(new PrivilegeService()), ;

    private PrivilegeServiceLocal privilegeService;

    PrivilegeServiceFactory(PrivilegeServiceLocal privilegeService){
        this.privilegeService = privilegeService;
    }

    public PrivilegeServiceLocal getPrivilegeService() {
        return privilegeService;
    }
}
