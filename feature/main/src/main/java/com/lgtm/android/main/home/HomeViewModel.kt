package com.lgtm.android.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lgtm.android.common_ui.base.BaseViewModel
import com.lgtm.domain.entity.response.SduiItemVO
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    // todo add repository
) : BaseViewModel() {

    private val _sduiList = MutableLiveData<List<SduiItemVO>>()
    val sduiList: LiveData<List<SduiItemVO>> = _sduiList
//
//    fun getHomeInfo() {
//        viewModelScope.launch {
//            val value = listOf(
//                SduiItemVO(
//                    viewType = "title",
//                    content = "content"
//                )
//            )
//            _sduiList.postValue(value)
//            Log.d(ContentValues.TAG, "getHomeInfo: $value")
//        }
//    }

}