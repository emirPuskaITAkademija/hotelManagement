package ba.reservation.hotelmanagement.business.room;

public enum RoomServiceFactory {

    SERVICE(new RoomService());

    private RoomService roomService;

    RoomServiceFactory(RoomService roomService){
        this.roomService = roomService;
    }

    public RoomService getRoomService() {
        return roomService;
    }
}
