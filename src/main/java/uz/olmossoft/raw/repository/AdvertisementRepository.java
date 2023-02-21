package uz.olmossoft.raw.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.olmossoft.raw.domain.Advertisement;
import uz.olmossoft.raw.enums.ModStatus;
import uz.olmossoft.raw.enums.Status;

/**
 * @author Muhammadkomil Murodillayev, ср 18:29. 1/11/23
 */

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    Page<Advertisement> findAllByStatusAndModStatus(Status status, ModStatus modStatus, Pageable pageable);


//        @Query(value = "select ad.id," +
//            "       ad.name," +
//            "       ad.description," +
//            "       locale_to_text(coalesce(:locale, 'uz'), r.name1, r.name2, r.name3, r.name4) as region," +
//            "       locale_to_text(coalesce(:locale, 'uz'), d.name1, d.name2, d.name3, d.name4) as district" +
//            "from advertisement ad" +
//            "         left join lists r on r.id = ad.region_id and r.type_id = 1" +
//            "         left join lists d on d.id = ad.district_id and d.type_id = 2" +
//            "where ad.deleted is false" +
//            "  and (:region_id is null or ad.region_id = :region_id)" +
//            "  and (:district_id is null or ad.district_id = :district_id)" +
//            "  and (:strat_date is null or ad.created_at::date >= :strat_date::date)" +
//            "  and (:end_date is null or ad.created_at::date <= :end_date::date)",
//            nativeQuery = true)
//    List<Advertisement> findAllByFilter(AdvertisementFilterDTO filterDTO);









}
