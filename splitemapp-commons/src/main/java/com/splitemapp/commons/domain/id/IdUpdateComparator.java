package com.splitemapp.commons.domain.id;

import java.util.Comparator;

/**
 * This is a descending order IdUpdate comparator. Helps ordering a list from greater OldId to smaller OldId
 * @author nicolas
 *
 */
public class IdUpdateComparator  implements Comparator<IdUpdate<Long>>{

	@Override
	public int compare(IdUpdate<Long> o1, IdUpdate<Long> o2) {
		return (int)(o2.getOldId().longValue() - o1.getOldId().longValue());
	}

}
