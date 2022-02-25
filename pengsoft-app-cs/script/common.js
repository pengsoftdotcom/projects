var api_url = {
    login: "oauth/token", //登录
    register: "service/security/sign-up", //注册
    getUserInfo: "service/security/user/me", //查询用户的个人资料
    getNewsListForPage: "service/misc/news/page", //分页获取新闻列表
    getNewsDetail: "service/misc/news", //查询新闻详情
    fileUpload: "transfer/upload", //上传文件
    getCode: "service/security/send-code", //发送验证码
    findPassword: "service/security/sign-up/find-password", //找回密码验证
    changePassword: "service/security/user/change-password", //修改密码
    addEvent: "service/event/event/submit", //新增报事
    getEventType: "service/event/event-catalog/find-tree", //获取报事类型
    getAdjacentGrid: "service/event/event/brother-org", //查询相邻网格
    getAppVersion: "service/system/app-version/lastest", //获取版本更新信息

    addPatrol: "service/event/const-checkpoint-event/submit",  //提交巡查
    getConstructionList: "service/event/checkpoint-group/all",  //获取工地列表
    getHomeSite: "service/event/checkpoint-group/constructions",   //获取首页工地列表
    getCLLocation: "service/event/checkpoint-group-provide/listnew",  //根据点位获取工地列表
    getSimplePoint: "service/event/checkpoint-group-provide/checkpoints",  //获取点位信息，不包括详情
    getActivePoint: "service/const/checkpoint/actives",   //获取激活点位
    getAllState: "service/event/checkpoint-event/all-status",  //获取所有的状态
    getPointList: "service/const/checkpoint/all",  //根据工地id获取点位列表
    getFloorDetail: "service/event/const-checkpoint-event/checkpointsteps",  //获取楼层检查点详情
    addFloorPatrol: "service/const/step-event/constsubmit",  //楼层检查点报事

    getSiteSummary: "service/event/checkpoint-group/count-by-inspect-status",  //获取巡查工地的统计
    getPointSummary: "service/const/checkpoint/count-by-inspect-status", //获取巡查点位的统计
    getSafeSummary: "service/event/checkpoint-event/count-by-catalog", //获取安全事件的统计
    getSiteMngSummary: "service/const/checkpointgroup-status-apply/const-manage",  //获取工地管理统计数据

    getPointAll: "service/const/checkpoint/activetotalinfo", //获取巡查点位总数
    getCompletelist:"service/const/checkpoint/activeinspectinfo", //获取已巡查点位
    getUnCompletelist:"service/const/checkpoint/activenoinspectinfo",//获取未巡查点位
    getPointDetail: "service/const/checkpoint/listinfo", //获取巡查点位详情
    getConts: "service/const/checkpoint/eventList", //分页查询报事列表
    getInspectDays: "service/const/checkpoint/inspectdays", //累计巡查天数
    getNoinspectdaysList: "service/const/checkpoint/noinspectdaysList", //未巡查次数

    getPatrolList: "service/const/step-event/page",  //获取巡查记录列表
    getPatrolDetail: "service/event/const-checkpoint-event",  //查询单个点的巡查记录
    completePatrol: "service/event/const-checkpoint-event/finish",  //完成巡查记录
    siteDetail1: "service/event/const-checkpoint-group",    //获取工地详情1
    getFloorPD: "service/const/step-event",  //查询楼层检查点详情
    completeFloor: "service/const/step-event/finish",  //完成楼层检查点报事

    //通讯录
    getConnectionList: "service/event/employee-contractbook/page", //获取通讯录列表

    //签到
    canSignUp: "service/const/sign/isallow",   //是否可以签到
    signUp: "service/const/sign/coding",    //签到接口
    signHistory: "service/const/sign/record",   //签到历史记录

    //天气
    getWeather: "service/event/weather/info",  //获取天气情况

    //未巡查统计
    noPatrolPeopleSummary: "service/event/noinspected-statistics/countnum",  //未巡查人员统计
    noPatrolSiteSummary: "service/event/noinspected-statistics/groupcount",  //未巡查工地统计
    noPatrolPeopleDetail: "service/event/noinspected-statistics/countnumdetail",  //未巡查人员详情
    noPatrolSite: "service/event/noinspected-statistics/groupList",  //未巡查工地列表
    noPatrolSiteDetail: "service/event/noinspected-statistics/userList",  //未巡查工地人员详情
    realcontrolNum: "service/event/noinspected-statistics/realNum",   //实控人未巡查记录
    realControlNPList: "service/event/noinspected-statistics/realuserList",   //实控人未巡查列表

    getSiteCount: "service/event/checkpoint-group/exsit-muilt",  //判断是否是多个工地

    //停复工
    applyForOff: "service/const/checkpointgroup-status-apply/apply",  //停复工申请
    getTypeInfo: "service/const/checkpointgroup-status-apply/get-typeinfo",  //获取类型信息
    getReasonInfo: "service/const/checkpointgroup-status-apply/get-typereasoninfo",  //获取类型对应的原因
    getApplyData: "service/const/checkpointgroup-status-apply/get-basedatainfo",  //获取申请类型
    getAllApply: "service/const/checkpointgroup-status-apply/list",   //获取停工复工申请列表
    getApplyDetails: "service/const/checkpointgroup-status-apply/detail",   //获取申请详情
    submitReply: "service/const/checkpointgroup-status-apply/handel",   //提交回复
    getNoReplyCount: "service/const/checkpointgroup-status-apply/nohandleNum",  //查询未批复事件数
    getAllCount: "service/notify/nums", //查询未批复事件数,系统通知，停复工申请
    getRead: "service/notify/readNums", //已读条数
    getPartApply: "service/const/checkpointgroup-status-apply/search-list", //获取某类型申请
    //施工进度
    getSiteList: "service/event/checkpoint-group/list-schedule",   //分页获取工地列表
    getSiteBuildings: "service/event/checkpoint-group/builder-info",  //获取工地楼栋信息
    getFloorInfo: "service/event/checkpoint-group/floor-info",    //获取某一栋楼楼层信息
    getFixPoint: "service/event/checkpoint-group/fixpoint-info",  //固定点进度管理

    //激活关闭巡查点
    getPointStatus: "service/const/checkpoint/status-info",  //获取巡查点激活状态
    activePoint: "service/const/checkpoint/active-status",   //激活巡查点
    changeState: "service/event/checkpoint-group/switch",   //巡查点启用、失效
    changeFloorState: "service/event/const-checkpoint-group/switch",  //楼层完工
    noactivePoint:"service/event/checkpoint-group/switch", //巡查部位失效

    //统计信息
    getMapPoint:"service/constr-site/get-constr-info", //巡查部位失效
    getHJdata:"service/constr-site/get-env-data", //环境监测
    getAQdata:"service/constr-site/find-all-inspect-info", //安全巡查情况
    getAlarm: "service/constr-site/get-dapin-history", //获取报警数据信息
    getAttendace: "service/constr-site/get-dapin-attendance",  //获取人员考勤信息
    getTotalInfo: "service/constr-site/get-total-project-info", //获取项目总体情况
    getWDGCFB: "service/constr-site/get-dashboard-status",  //获取危大工程各阶段分布情况
    getVedioInfo: "service/constr-site/get-video-info",  // 获取视频情况
    getLifter: "service/constr-site/get-lifter-info",  //  获取施工升降机情况
    getTower: "service/constr-site/get-tower-info",  // 获取塔机情况
    addUserSign:"service/const/super-sign/sign",

    getMyContractList: "service/const/checkpoint-group-employee-contract/my-contracts",
    contractConfirm: "service/const/checkpoint-group-employee-contract/confirm",

    getMySalaryList: "service/const/checkpoint-group-employee-salary/my-salaries",
    salaryConfirm: "service/const/checkpoint-group-employee-salary/confirm",

    getMyCheckpointGroups: "service/event/checkpoint-group-employee/my-checkpoint-groups", // 我的工地
    getEmployeesByCheckpointGroup: "service/event/checkpoint-group-employee/employees-by-checkpoint-group", //工地员工

    submitSafetyTraining: "service/const/checkpoint-group-safety-training/submit", // 提交安全培训
    mySafetyTrainings: "service/const/checkpoint-group-safety-training/my-safety-trainings",  //安全培训列表
};

var api_role = {

    //协管员（员工端）
    ROL_GUARD: "ROL_GUARD",

    //社区工作人员（员工端）
    ROL_COMM_CHARGE: "ROL_COMM_CHARGE",

    //街道管理员（管理端）
    ROL_TOWN_CHARGE: "ROL_TOWN_CHARGE",

    //清洁经理（管理端）
    ROL_CLEANER_MANAGER: "ROL_CLEANER_MANAGER",

    //网格长（员工端）
    ROL_GRID_MANAGER: "ROL_GRID_MANAGER",

    //一般员工（员工端）
    ROL_STAFF: "ROL_STAFF",

    //社区管理人员（管理端）
    ROL_COMMUNITY_MANAGER: "ROL_COMMUNITY_MANAGER",

    //直属机构主管--区级部门主管（高层管理端）
    ROL_CITY_MANAGEMENT_CHARGE: "ROL_CITY_MANAGEMENT_CHARGE",

    //直属机构工作人员--区级部门工作人员（高层管理端）
    ROL_TOP: "ROL_TOP",

    //采点（采集端）
    ROL_POINT: "ROL_POINT",

    //网格员（员工端）
    ROL_GRIDDER: "ROL_GRIDDER",

    //清洁工（员工端）
    ROL_CLEANER: "ROL_CLEANER",

    //清洁管理（管理端）
    ROL_CLEANER_CHARGE: "ROL_CLEANER_CHARGE",

    //督察小组（员工端）
    ROL_INSPECTION_TEAM: "ROL_INSPECTION_TEAM"
};

//ajax请求
function Ajax(obj) {

    if (obj) {

        //url
        if (!obj.url) {
            throw new Error("ajax请求的地址不能为空！");
        }

        //beforeSend
        if (obj.beforeSend) {
            obj.beforeSend();
        } else {
            api.showProgress({
                animationType: "zoom"
            });
        }

        //type
        var type = "get";
        if (obj.type) {
            type = obj.type.toLocaleLowerCase();
        }

        var tag = "";
        if(obj.tag){
        	tag = obj.tag;
        }

        //data
        var data = new Object();
        if (obj.data) {
            if (type == "get") {
                if (obj.url.indexOf("?") > 0) {
                    for (var key in obj.data) {
                        obj.url += "&" + key + "=" + obj.data[key];
                    }
                } else {
                    obj.url += "?";
                    var key_arr = new Array();
                    for (var key in obj.data) {
                        key_arr.push(key)
                    }
                    for (var i = 0; i < key_arr.length; i++) {
                        obj.url += (i == 0 ? "" : "&") + key_arr[i] + "=" + obj.data[key_arr[i]];
                    }
                }
            } else if (type == "post") {
                data = (obj.data ? obj.data : data);
            }
        }
        //ajax
        api.ajax({
            method: type,
            url: obj.url,
            tag: tag,
            headers: obj.headers ? obj.headers : {}, //headers
            dataType: obj.dataType ? obj.dataType : "json", //dataType
            report: obj.report ? obj.report : false, //report
            data: {
                values: data, //data 表单方式
                files: obj.files ? obj.files : {} //files
            },
            timeout: obj.timeout ? obj.timeout : 30 //timeout
        }, function (ret, err) {
            //complete
            if (obj.complete) {
                obj.complete();
            } else {
                api.hideProgress();
            }

            if (ret) {

                if (obj.success) {
                    obj.success(ret);   //success
                }

            } else {

                //判断是否需要更新access_token
                if (err.statusCode == 401 && err.body.error.code == "ACCESS_TOKEN_EXPIRED") {

                    //updateAccessToken(obj);

                    var interface_url = $api.getStorage("interface_url");
                    var patrolLists = $api.getStorage("PatrolLists");
                    $api.clearStorage();
                    $api.setStorage("PatrolLists",patrolLists);
                    $api.setStorage("interface_url", interface_url);

                    //跳转登录页
                    var script = "";
                    script += "closeFrm(\"home_frm\");";
                    script += "openWin(\"login_win\");";
                    script += "closeWin(\"user/center_win\");";

                    api.execScript({
                        name: "root",
                        script: script
                    });

                } else {
                    if (obj.error) {
                        obj.error(err);  //error
                    } else {
                        switch (err.statusCode) {

                            case 0:
                                if (vm && vm.timeOut) { vm.timeOut = true; }
                                api.toast({ msg: "请求超时！请检查网络连接", duration: 3000 });
                                break;
                            case 404:
                                api.alert({ title: "错误", msg: "请求地址错误！", buttons: ["确定"] });
                                break;
                            default:
                                api.alert({ title: "错误", msg: JSON.stringify(err), buttons: ["确定"] });
                                break;
                        }

                    }

                }

            }

        });

    } else {
        throw new Error("ajax的参数不能为空！");
    }

};

//打开win
function openWin(name, param) {
    api.openWin({
        name: name,
        url: "widget://html/" + name + ".html",
        delay: 300,
        pageParam: param ? param : {},
        slidBackEnabled: name == "login_win" ? false : true
    });
};

//关闭win
function closeWin(name) {

    if (name) {
        api.closeWin({
            name: name
        });
    } else {
        api.closeWin();
    }

};

//关闭frm
function closeFrm(name) {

    if (name) {
        api.closeFrame({
            name: name
        });
    } else {
        api.closeFrame();
    }

};

//将null等输出为空字符串
function toEmpty(obj) {
    return obj ? obj : "";
};

//判断是否登录
function isLogin() {

    var access_token = $api.getStorage("access_token");
    var user = $api.getStorage("user");

    if (user && access_token) {
        return true;
    } else {
        return false;
    }

};

//判断角色
function getRoleCode() {

    if (isLogin()) {

        var user = $api.getStorage("user");
        var code = user.roleList.length > 0 && user.roleList[0].code ? user.roleList[0].code : "";

        //员工端数据
        var staff_array = [api_role.ROL_GUARD, api_role.ROL_COMM_CHARGE, api_role.ROL_GRID_MANAGER, api_role.ROL_STAFF, api_role.ROL_GRIDDER, api_role.ROL_CLEANER, api_role.ROL_INSPECTION_TEAM];

        //管理端数据
        var admin_array = [api_role.ROL_TOWN_CHARGE, api_role.ROL_CLEANER_MANAGER, api_role.ROL_COMMUNITY_MANAGER, api_role.ROL_CLEANER_CHARGE];

        //高层管理端数据
        var superadmin_array = [api_role.ROL_CITY_MANAGEMENT_CHARGE, api_role.ROL_TOP];

        if (staff_array.indexOf(code) >= 0) {
            return 1;
        } else if (admin_array.indexOf(code) >= 0) {
            return 3;
        } else if (superadmin_array.indexOf(code) >= 0) {
            return 5;
        }

    } else {
        throw new Error("用户信息为空，请重新登录！");
    }

};

//更新access_token
function updateAccessToken(obj) {

    Ajax({
        type: "post",
        url: $api.getStorage("interface_url") + api_url.login,
        data: {
            client_id: api.systemType == "android" ? "ma" : "mi",
            client_secret: api.systemType == "android" ? "ma" : "mi",
            grant_type: "refresh_token",
            refresh_token: $api.getStorage("refresh_token")
        },
        beforeSend: function () { },
        complete: function () { },
        success: function (data) {

            if (data.success) {

                $api.setStorage("access_token", data.data.access_token);
                $api.setStorage("refresh_token", data.data.refresh_token);

                //继续进行上次操作
                //Ajax(obj);

            } else {
                api.alert({ title: "错误", msg: "会话已过期，请重新登录！", buttons: ["确定"] });
            }

        }, error: function () {

        }
    });

};

//返回缩略图地址
function thumb(path, width, height) {

    if (path && path.indexOf("http://image") == 0 && (width || height)) {

        path = path.replace("http://image", "http://thumb");

        var index = path.lastIndexOf(".");
        var suffix = "";

        if (index != -1) {
            suffix = path.substring(index);
        }
        path += "@1e";

        if (width) {
            path += "_" + width + "w";
        }

        if (height) {
            path += "_" + height + "h";
        }

        path += suffix;

    }
    return path;

};

//移除极光推送事件监听
function stopAjpush() {

    var ajpush = api.require("ajpush");
    ajpush.removeListener();

};

//设置0.3秒后执行代码
function timeOut300(func) {

    setTimeout(function () { func(); }, 300);

};

//设置状态栏的文字颜色和背景颜色
function setStatusBarStyle(style, bgColor) {

    $api.fixIos7Bar($api.byId("header"));
    $api.fixStatusBar($api.byId("header"));

    if (bgColor) {
        api.setStatusBarStyle({ style: style, color: bgColor });
    } else {
        api.setStatusBarStyle({ style: style });
    }

};

//打开侧滑布局
function openSlidLayout() {

	if(api.systemType == "ios"){
		api.closeWin({name:'slidLayout'});
	}

    api.openSlidLayout({
        type: "left",
        slidPaneStyle: {
            leftEdge: 60
        },
        fixedPane: {
            name: "menu_win",
            url: "widget://html/menu_win.html",
            bgColor: "#fff",
            bounces: false,
            vScrollBarEnabled: true,
            hScrollBarEnabled: false
        },
        slidPane: {
            name: "home_win",
            url: "widget://html/home_win.html",
            bgColor: "#fff",
            bounces: false,
            vScrollBarEnabled: true,
            hScrollBarEnabled: false
        }
    },function(ret, err){
        	if(ret && ret.event == "open"){
        		api.execScript({
	                name: "menu_win",
	                script: "getBubbles();"
	            });
			}
        }
    );

};

//按钮点击特效
function effect() {

    Vue.directive("effect", {
        bind: function () {
            var el = this.el
            el.classList.add("waves-effect")
            this.expression && el.classList.add("waves-" + this.expression)
            function convertStyle(obj) {
                var style = "";
                for (var a in obj) {
                    if (obj.hasOwnProperty(a)) {
                        style += (a + ":" + obj[a] + ";");
                    }
                }
                return style;
            }
            this.handler = function (e) {
                var ripple = document.createElement("div");
                ripple.classList.add("waves-ripple");
                el.appendChild(ripple);
                var styles = {
                    "left": e.layerX + "px",
                    "top": e.layerY + "px",
                    "opacity": 1,
                    "transform": "scale(" + ((el.clientWidth / 100) * 10) + ")",
                    "transition-duration": "750ms",
                    "transition-timing-function": "cubic-bezier(0.250, 0.460, 0.450, 0.940)"
                };
                ripple.setAttribute("style", convertStyle(styles));
                setTimeout(function () {
                    ripple.setAttribute("style", convertStyle({
                        "opacity": 0,
                        "transform": styles.transform,
                        "left": styles.left,
                        "top": styles.top
                    }));
                    setTimeout(function () {
                        ripple && el.removeChild(ripple);
                    }, 750);
                }, 450);
            }
            this.el.addEventListener("mousedown", this.handler, false)
        },
        unbind: function () {
            this.el.removeEventListener("mousedown", this.handler)
        }
    });

};
