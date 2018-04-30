package jp.co.websupport.memo.entity;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;
/*
 * JSFのListDataModelクラスを継承して
	PrimeFacesのSelectableDataModelインタフェースを実装
 */
public class IdEntityListDataModel<T extends IdEnitity> extends ListDataModel<T> implements SelectableDataModel<T> {


	//コンストラクタ
	public IdEntityListDataModel(List<T> data) {
		super((List<T>)data);
	}
	@Override
	public Object getRowKey(T entity) {

		return entity.getId();
	}

	@Override
	public T getRowData(String rowKey) {
		@SuppressWarnings("unchecked")
		List<T> entities=(List<T>) getWrappedData();
		long id=Long.parseLong(rowKey);
		for(T entity : entities){
			if(entity.getId()==id) {
				return (T)entity;
			}
		}
		return null;
	}


}
