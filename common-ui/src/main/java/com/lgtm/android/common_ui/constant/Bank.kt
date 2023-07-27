package com.lgtm.android.common_ui.constant

import com.lgtm.android.common_ui.R
import com.lgtm.domain.constants.BankVO

interface BankList

data class BankHint(
    val hint: String
) : BankList

enum class Bank(val bankVO: BankVO, val icon: Int? = null) : BankList {
    NH(BankVO.NH, R.drawable.ic_btn_nh),
    KAKAO(BankVO.KAKAO, R.drawable.ic_btn_kakao),
    KB(BankVO.KB, R.drawable.ic_btn_kb),
    SHINHAN(BankVO.SHINHAN, R.drawable.ic_btn_sinhan),
    WOORI(BankVO.WOORI, R.drawable.ic_btn_wr),
    TOSS(BankVO.TOSS, R.drawable.ic_btn_tb),
    IBK(BankVO.IBK, R.drawable.ic_btn_ibk),
    HANA(BankVO.HANA, R.drawable.ic_btn_hn),
    KFCC(BankVO.KFCC, R.drawable.ic_btn_sme),
    BUSAN(BankVO.BUSAN, R.drawable.ic_btn_bs),
    DAEGU(BankVO.DAEGU, R.drawable.ic_btn_dgb),
    K_BANK(BankVO.K_BANK, R.drawable.ic_btn_k),
    SHINHYUP(BankVO.SHINHYUP, R.drawable.ic_btn_sh),
    POST(BankVO.POST, R.drawable.ic_btn_post),
    SC(BankVO.SC, R.drawable.ic_btn_sc),
    KYUNGNAM(BankVO.KYUNGNAM, R.drawable.ic_btn_bs),
    GWANGJU(BankVO.GWANGJU, R.drawable.ic_btn_gj),
    SUHYUP(BankVO.SUHYUP, R.drawable.ic_bank_suhyup),
    JEONBUK(BankVO.JEONBUK, R.drawable.ic_btn_gj),
    FSB(BankVO.FSB, R.drawable.ic_btn_jc),
    JEJU(BankVO.JEJU, R.drawable.ic_btn_sinhan),
    CITY(BankVO.CITY, R.drawable.ic_btn_ct),
    KDB(BankVO.KDB, R.drawable.ic_btn_kdb),
    NFCF(BankVO.NFCF, R.drawable.ic_btn_sljh),
    SBI(BankVO.SBI, R.drawable.ic_btn_sbi),
    BOA(BankVO.BOA, R.drawable.ic_btn_boa),
    CHINA(BankVO.CHINA, R.drawable.ic_btn_china),
    HSBC(BankVO.HSBC, R.drawable.ic_btn_hsbc),
    ICBC(BankVO.ICBC, R.drawable.ic_btn_icbc),
    DEUTSCHE(BankVO.DEUTSCHE, R.drawable.ic_btn_doichi),
    JP_MORGAN(BankVO.JP_MORGAN, R.drawable.ic_btn_jp),
    BNP(BankVO.BNP, R.drawable.ic_btn_bnp),
    CHINA_CONSTRUCTION(BankVO.CHINA_CONSTRUCTION, R.drawable.ic_btn_china_construction);

    companion object {
        fun getBankList() = values().toList()
    }

}