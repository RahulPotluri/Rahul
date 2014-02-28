using System;
using System.Collections;
using System.Web.Script.Services;
using System.Web.Services.Protocols;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using MySql.Data.MySqlClient;
using MySql.Data;
using System.Data;

namespace buy
{
    /// <summary>
    /// Summary description for WebService1
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    [System.Web.Script.Services.ScriptService]
    public class WebService1 : System.Web.Services.WebService
    {
        
        [WebMethod]
        public List<string> GetItem(string prefixText)
        {
            MySqlConnection conn;
            string server, database, uid, password;
            server = "localhost";
            database = "supermarket";
            uid = "root";
            password = "admin";
            int port = 3307;
            string ConnectionString = "SERVER=" + server + ";" + "Port=" + port + ";" + "DATABASE=" +
                     database + ";" + "UID=" + uid + ";" + "PASSWORD=" + password + ";";
            conn = new MySqlConnection(ConnectionString);
            conn.Open();
            MySqlCommand cmd = new MySqlCommand();
            cmd.Connection = conn;
            string str = prefixText;
             cmd.CommandText = "select ItemName from items where ItemName like '"+prefixText+"%'";
          
            MySqlDataAdapter da = new MySqlDataAdapter(cmd);
            DataSet ds = new DataSet();
            da.Fill(ds);
            List<string> item = new List<string>();
            for (int i = 0; i < ds.Tables[0].Rows.Count; i++)
            {
                item.Add(ds.Tables[0].Rows[i][0].ToString());
            }
            if (item.Count == 0)
            {
                item.Add("Well this is sad. We didnot find anything. Try something different.");
            }

            return item;

        }
    }
}
