package ba.reservation.hotelmanagement.business.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservation")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
        @NamedQuery(name = "Reservation.findById", query = "SELECT r FROM Reservation r WHERE r.id = :id"),
        @NamedQuery(name = "Reservation.findByFromDate", query = "SELECT r FROM Reservation r WHERE r.fromDate = :fromDate"),
        @NamedQuery(name = "Reservation.findByToDate", query = "SELECT r FROM Reservation r WHERE r.toDate = :toDate"),
        @NamedQuery(name = "Reservation.findByCheckIn", query = "SELECT r FROM Reservation r WHERE r.checkIn = :checkIn"),
        @NamedQuery(name = "Reservation.findByCheckOut", query = "SELECT r FROM Reservation r WHERE r.checkOut = :checkOut"),
        @NamedQuery(name = "Reservation.findByCheckInDateTime", query = "SELECT r FROM Reservation r WHERE r.checkInDateTime = :checkInDateTime"),
        @NamedQuery(name = "Reservation.findByCheckOutDateTime", query = "SELECT r FROM Reservation r WHERE r.checkOutDateTime = :checkOutDateTime"),
        @NamedQuery(name = "Reservation.findByPrice", query = "SELECT r FROM Reservation r WHERE r.price = :price"),
        @NamedQuery(name = "Reservation.findByStatus", query = "SELECT r FROM Reservation r WHERE r.status = :status")})
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "from_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromDate;
    @Basic(optional = false)
    @Column(name = "to_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toDate;
    @Basic(optional = false)
    @Column(name = "check_in")
    private short checkIn;
    @Basic(optional = false)
    @Column(name = "check_out")
    private short checkOut;
    @Column(name = "check_in_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkInDateTime;
    @Column(name = "check_out_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOutDateTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @JoinTable(name = "reservation_guest", joinColumns = {
            @JoinColumn(name = "id_reservation", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "id_guest", referencedColumnName = "id")})
    @ManyToMany
    private List<Guest> guestList;
    @JoinColumn(name = "id_guest", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Guest idGuest;
    @JoinColumn(name = "id_room", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Room idRoom;

    public Reservation() {
    }

    public Reservation(Integer id) {
        this.id = id;
    }

    public Reservation(Integer id, Date fromDate, Date toDate, short checkIn, short checkOut, BigDecimal price, int status) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.price = price;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public short getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(short checkIn) {
        this.checkIn = checkIn;
    }

    public short getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(short checkOut) {
        this.checkOut = checkOut;
    }

    public Date getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(Date checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }

    public Date getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public void setCheckOutDateTime(Date checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public List<Guest> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<Guest> guestList) {
        this.guestList = guestList;
    }

    public Guest getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(Guest idGuest) {
        this.idGuest = idGuest;
    }

    public Room getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Room idRoom) {
        this.idRoom = idRoom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", checkInDateTime=" + checkInDateTime +
                ", checkOutDateTime=" + checkOutDateTime +
                ", price=" + price +
                ", status=" + status +
                ", guestList=" + guestList +
                ", idGuest=" + idGuest +
                ", idRoom=" + idRoom +
                '}';
    }
}

