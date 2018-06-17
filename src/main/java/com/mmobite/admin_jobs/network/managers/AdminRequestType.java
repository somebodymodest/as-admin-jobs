package com.mmobite.admin_jobs.network.managers;

import java.util.HashMap;
import java.util.Map;

public enum AdminRequestType {
    Dummy(0),
    CheckCharacter(1),
    SetCharacterLocation(2),
    SetBuilderCharacter(3),
    ChangeCharacterName(4),
    KickCharacter(5),
    AddSkill(6),
    DelSkill(7),
    ModSkill(8),
    SetOnetimeQuest(9),
    SetQuest(10),
    DelQuest(11),
    AddItem(12),
    DelItem(13),
    ModItem(14),
    ModChar(15),
    ModChar2(16),
    ModCharTitle(17),
    PunishChar(18),
    SetBuilderAccount(19),
    DisableCharacter(20),
    EnableCharacter(21),
    GetCharacters(22),
    SetBookMark(23),
    DelBookMark(24),
    DelPledgeCrest(25),
    ModPledgeName(26),
    SetProhibited(27),
    SeizeItem(28),
    ModChar3(29),
    MoveItem(30),
    MoveCharacter(31),
    CommentWrite(32),
    CommentDelete(33),
    DeleteChar(34),
    RestoreChar(35),
    PledgeOust(36),
    PledgeChangeOwner(37),
    PledgeDelete(38),
    BanChar(39),
    MoveItem2(40),
    PrintAllAccountData(41),
    PrintAllItemData(42),
    CopyChar(43),
    CreatePet(44),
    SendHome(45),
    ChangePledgeLevel(46),
    CreatePledge(47),
    SetSkillAll(48),
    RestoreChar2(49),
    ManAnnounce(50),
    ManIntAnnounce(51),
    ModWeekPlay(52),
    SeizeItem2(53),
    DelItem2(54),
    AddItem2(55),
    GetCharacters2(56),
    DelMacro(57),
    DelMonRaceTicket(58),
    DelRecipe(59),
    DelLotto(60),
    ModifyPledgePower(61),
    EventPoint(62),
    GetPledge(63),
    EventPoint2(64),
    CreateCharacter(65),
    AddItems(66),
    AddSkills(67),
    GetCharacters3(68),
    SetSociality(69),
    SetInstantAnnounce(70),
    DelHenna(71),
    AddHenna(72),
    ReserveCharName(73),
    AddHennas(74),
    GetCastleList(75),
    ModifyCharProperty(76),
    ModifyCharAbilityDelta(77),
    ModifyCharAbility(78),
    DelHenna2(79),
    AddHenna2(80),
    AddSkill2(81),
    DelSkill2(82),
    ModifySkill2(83),
    AddMacro(84),
    GetSSQStatus(85),
    GetSSQMainEventRecord(86),
    ModifyDepositedSSQItem(87),
    ChangePetName(88),
    ChangeSubJob(89),
    StopChar(90),
    CancelPersonalShop(91),
    AddMacroInfo(92),
    CreateCharacter2(93),
    AddSkills2(94),
    AddHennas2(95),
    AddSubjobs(96),
    GetPledge2(97),
    DelPledgeEmblem(98),
    RegisterAccount(99),
    DelPledgeAnnounce(100),
    SendPrivateAnnounce(101),
    GetAgitList(102),
    GetPledgeMember(103),
    EternalBan(104),
    GetCharacters4(105),
    SetNobless(106),
    SetHero(107),
    SetPartyLocation(108),
    ModOlympiadPoint(109),
    // AdvExt
    SendMessageToGame(115);

    private final int id;
    private static Map<Integer, AdminRequestType> map = new HashMap<Integer, AdminRequestType>();

    AdminRequestType(int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }

    static {
        for (AdminRequestType adminRequestType : AdminRequestType.values()) {
            map.put(adminRequestType.id, adminRequestType);
        }
    }

    public static AdminRequestType valueOf(Integer adminJobType) {
        return map.get(adminJobType);
    }
}
