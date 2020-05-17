import { format, parse } from 'date-fns';

export class DateUtils {

  static formatDate(date: any): string {
    return format(date, 'YYYY-MM-DD');
  }

  static formatTime(time: any): string {
    return format(time, 'HH:mm:ss');
  }

  static formatDateTime(dateTime: any): string {
    return format(dateTime, 'YYYY-MM-DD HH:mm:ss');
  }

  static parse(dateTime: string): Date {
    return parse(dateTime, 'YYYY-MM-DD HH:mm:ss', new Date());
  }

}
