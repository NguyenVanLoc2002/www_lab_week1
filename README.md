# www_lab_week1
Thực hành WWW, tuần 01
I. Đăng nhập 
  - Ở giao diện màn hình chính hiên thị form(<form action="ControllerServlet" method="post">) logon ở file index.jsp
  - Khi nhập tên tài khoản, mật khẩu và ấn nút logon nó sẽ đến phương thức doPost của ControllerServlet để xử lý
  - Khi kiểm tra action nào có tên là logon thì nó sẽ thực thi hàm  logon(req, resp)
  - Hàm xử lí logon:
    + Lấy username và password thông qua req.getParameter
    + Lấy tài khoản theo tên tài khoản: Account account = accountReponsitory.getByUserName(username).orElse(null);
    + Kiểm tra xem account khác null và có password khớp với tài khoản đó:
      Nếu thỏa điều kiện thì ghi log lại vào CSDL set thời gian đăng nhập với thời gian logout bằng thời gian hiện tại(khi nào logout sẽ cập nhật lại thời gian        logout) đồng thời kiểm tra role của account
          if (checkRoleAdmin(account.getId())) --> chuyển đến trang dashboard
          ngược lại chuyển đến trang accountInfo
      Ngược lại chuyển hướng đến trang đăng nhập với thông báo lỗi resp.sendRedirect("index.jsp?error=Not Found");
II. Ở giao diện dashboard quản lí tài khoản
  1. Xem tài khoản
  - Khi click vào nút <a href="ControllerServlet?action=getListAccount">Xem tài khoản</a> chuyển đến phương thức doGet của lớp ControllerServlet
  - Tại phương thức doGet:
    + Lấy danh sách cách account: List<Account> listAccount = accountReponsitory.getAll(); 
    + Kiểm tra nếu action nào bằng "getListAccount" thì thêm vào session 1 listAccount và chuyển đến trang managerAccount.jsp
  - Tại giao diện managerAccount.jsp dùng <c:forEach var="account" items="${listAccount}"> để hiển thị ra thông tin của từng account
    ví dụ: <li>User Name: ${account.full_name}</li>
  2. Xem quyền của các account
  - Khi click vào nút <a href="ControllerServlet?action=getListRoles">Xem quyền</a> chuyển đến phương thức doGet của lớp ControllerServlet
  - Tại phương thức doGet:
    + Lấy danh sách role: List<Role> roleList = roleReponsitory.getAll();
    + Kiểm tra nếu action nào bằng "getListRoles" thì thêm vào session 1 roleList và chuyển đến trang managerRole.jsp
  - Tại giao diện managerRole.jsp dùng  <c:forEach var="role" items="${roleList}"> để hiển thị ra thông tin role của từng account
    ví dụ: <li>Role Name: ${role.name}</li>
  3. Cấp quyền cho tài khoản
  - Khi click vào nút  <a href="ControllerServlet?action=grantRoles">Cấp quyền</a> chuyển đến phương thức doGet của lớp ControllerServlet
  - Tại phương thức doGet:
    + Lấy danh sách listAccount và roleList
    + Kiểm tra nếu action nào bằng "grantRoles" thì thêm vào session 1 roleList,1 listAccount và chuyển đến trang grantRoles.jsp
  - Tại giao diện grantRoles.jsp dùng:
    + Thẻ select chứa các tên account để người dùng có thể chọn cấp quyền
    + Thẻ select chứa các role để người dùng có thể cấp role
    + Sau khi ấn nút cấp quyền thì sẽ chuyển đến làm phương thức doPost trong ControllerServlet
  - Tại phương thức doPost:
    + Kiểm tra nếu action là "grantRole" thì xử lý
    + Tạo biến boolean rs = grantRole(req, resp);
    + Ở hàm grantRole:
      Lấy account_id và role_id thông qua req.getParameter
      Kiểm tra xem account và role có tồn tại hay không nếu tồn tại trả về false ngược lại true
      Kiểm tra xem đã có GrantAccess tương tự chưa nếu chưa có thì thêm mới còn ngược lại không làm gì cả
    + Kiểm tra  if (rs):
      Nếu true thì lấy danh sách grantAccessList rồi thêm vào session --> chuyển đến trang managerAccount
      Nếu false thì dùng session để lấy ra listAccount, roleList để hiển thị trong select của giao diện grantRoles và
      hiển thị thông báo Không thêm được trên đường dẫn resp.sendRedirect("grantRoles.jsp?action=grantRoles?error=KoThemDuoc");
    4. Logout
      <form action="ControllerServlet" method="post">
        <input type="submit" value="logout">
        <input type="hidden" name="action" value="logout">
    </form>
  - Khi click vào nút logout ở giao diện dashboard thì chuyển đến phương thức doPost của ControllerServlet để xử lý
  - Tại doPost kiểm tra action = "logout" thì :
    + Lấy danh sách các log: List<Logs> list  = logReponsitory.getAll();
    + Kiểm tra xem danh sách có rỗng hay không:
      Nếu không rỗng thì
        lấy ra log: Logs log = list.get(list.size() - 1);(lấy ra log vừa đăng nhập)
        Set lại thời gian logout cho log đó
        Chuyển đến trang index.jsp
III. Ở giao diện người dùng
  - Sau khi đăng nhập với tài khoản không phải quyền admin (xử lý trong hàm logon của ControllerServlet) thì chuyển đến giao diện loggedInAccount
  - Ở giao diện loggedInAccount dùng  <c:if test="${not empty loggedInAccount}"> hiển thị ra thông tin người dùng với 'loggedInAccount' được thêm vào
    session: session.setAttribute("loggedInAccount", account); (được xử lí hàm logon của ControllerServlet)
  - Logout xử lí giống như trên 
      
