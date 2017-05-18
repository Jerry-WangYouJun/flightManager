package com.mapping;

import java.util.List;

import com.pojo.Hotel;

public interface HotelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hotel record);

    int insertSelective(Hotel record);

    Hotel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hotel record);

    int updateByPrimaryKey(Hotel record);

	List<Hotel> selectAll();

	List<Hotel> selectHotelByWhere(Hotel hotel);

	Long selectHotelCountByWhere(Hotel hotel);
}