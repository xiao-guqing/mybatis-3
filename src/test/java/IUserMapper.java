import java.util.List;

/**
 * @author xiaoguqing
 * @date 2021/9/17 20:43
 */
public interface IUserMapper {

  public List<Object> findAllUser();

  public void updateByid();
}
